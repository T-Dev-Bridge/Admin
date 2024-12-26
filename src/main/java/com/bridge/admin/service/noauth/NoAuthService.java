package com.bridge.admin.service.noauth;


import com.bridge.admin.service.auth.ManagerDto;
import com.bridge.admin.service.auth.ManagerService;
import com.bridge.admin.service.auth.PasswordDto;
import com.bridge.admin.service.auth.PasswordService;
import com.bridge.admin.service.authClient.AuthClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bridge.base.exception.CommonException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoAuthService {

    private final ManagerService managerService;
    private final PasswordService passwordService;
    private final AuthClientService authClient;

    @Transactional
    public String signup(ManagerDto managerDto) throws CommonException {
        try{
            if(managerService.get(managerDto.getId()) != null){
                return "이미 존재하는 아이디입니다.";
            }
            managerDto.setPwdId(handlePasswordEncryption(managerDto.getPassword(), managerDto.getId()));
            managerDto.setEnabled(true);
            managerService.save(managerDto, managerDto.getId());
            return managerDto.getId();
        } catch (CommonException e){
            log.error(e.getMessage());
            return e.getMessage();
        }
    }

    // 패스워드 암호화 및 저장, 암호화된 패스워드의 ID 반환
    @Transactional
    public Long handlePasswordEncryption(String password, String userId) throws CommonException {
        String encryptedPassword = authClient.encodePassword(password);

        PasswordDto passwordDto = new PasswordDto();
        passwordDto.setLastChangedTime(LocalDateTime.now().toString());
        passwordDto.setExpTime(LocalDateTime.now().plusDays(90).toString());
        passwordDto.setPassword(encryptedPassword);

        PasswordDto savedPassword = passwordService.save(passwordDto, userId);

        return savedPassword.getId();
    }
}
