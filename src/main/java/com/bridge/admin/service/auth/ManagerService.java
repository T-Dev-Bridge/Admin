package com.bridge.admin.service.auth;

import com.bridge.admin.client.AuthClient;
import com.bridge.admin.repository.rdb.auth.Manager;
import com.bridge.admin.repository.rdb.auth.ManagerRepository;
import org.bridge.base.repository.rdb.CrdRepository;
import org.bridge.base.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
        ManagerDto managerDto = new ManagerDto(manager);
        managerDto.setId(manager.getId());
        managerDto.setUsername(manager.getUsername());
        managerDto.setPwdId(manager.getPwdId());
        managerDto.setPassword(manager.getPassword().getPassword());
        managerDto.setEnabled(manager.getEnabled() == 1);

        return managerDto;
    }

    public ManagerDto getByUsername(String username) {
        return ((ManagerRepository) this.repository).findByUsername(username).map(ManagerDto::new).orElse(null);
    }
}
