package com.bridge.admin.service.auth;

import com.bridge.admin.repository.rdb.auth.Password;
import lombok.extern.slf4j.Slf4j;
import org.bridge.base.repository.rdb.CrdRepository;
import org.bridge.base.repository.rdb.CrudRepository;
import org.bridge.base.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PasswordService extends CrudService<Password, PasswordDto, Long> {

    public PasswordService(CrudRepository<Password, Long> repository) { super(repository); }

    @Override
    @Autowired
    @Qualifier("passwordRepository")
    public void setRepository(CrdRepository repository) { this.repository = repository; }

    @Override
    public Class<?> getDtoClazz() { return PasswordDto.class; }

}
