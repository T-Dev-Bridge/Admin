package com.bridge.admin.service.auth;

import com.bridge.admin.client.AuthClient;
import com.bridge.admin.repository.rdb.auth.Manager;
import com.bridge.admin.repository.rdb.auth.ManagerRepository;
import com.bridge.admin.repository.rdb.auth.Password;
import org.bridge.base.exception.CommonException;
import org.bridge.base.repository.rdb.CrdRepository;
import org.bridge.base.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Qualifier("managerService")
public class ManagerService extends CrudService<Manager, ManagerDto, String> {

    private final PasswordService passwordService;
    private final AuthClient authClient;

    @Autowired
    public ManagerService(CrdRepository<Manager, String> repository,
                          PasswordService passwordService, AuthClient authClient) {
        super(repository);
        this.passwordService = passwordService;
        this.authClient = authClient;
    }

    /**
     * 검색 필드 추가, searchFields: 텍스트 검색을 수행할 Field 목록 ( Entity Column 명 )
     * numberFields: 숫자 검색을 수행할 Field 목록
     * InquiryService 에서 해당 로직 확인 가능
     */
    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        this.searchFields = new String[]{"username", "id", "email"};

        this.numberFields = new String[]{};

        super.init();
    }

    @Override
    @Autowired
    @Qualifier("managerRepository")
    public void setRepository(CrdRepository repository) { this.repository = repository; }

    @Override
    public Class<?> getDtoClazz() { return ManagerDto.class; }


    @Transactional(readOnly = true)
    public ManagerDto getManager(String managerId){
        Optional<Manager> managerOptional = this.repository.findById(managerId);
        if(!managerOptional.isPresent()){
            return null;
        }
        Manager manager = managerOptional.get();
        return new ManagerDto(manager);
    }

    public ManagerDto getByUsername(String username) {
        return ((ManagerRepository) this.repository).findByUsername(username).map(ManagerDto::new).orElse(null);
    }

    @Transactional
    public ManagerDto saveWithPassword(ManagerDto o, String userId) throws CommonException {
        String encryptPassword = authClient.encodePassword(o.getPassword()).getData();

        PasswordDto passwordDto = new PasswordDto();
        passwordDto.setPassword(encryptPassword);

        Password password = passwordService.save(passwordDto, userId).toEntity();

        o.setPwdId(password.getId());

        return this.save(o, userId);
    }
}
