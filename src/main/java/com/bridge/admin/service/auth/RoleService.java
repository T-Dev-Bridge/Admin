package com.bridge.admin.service.auth;

import com.bridge.admin.repository.rdb.auth.Role;
import lombok.extern.slf4j.Slf4j;
import org.bridge.base.repository.rdb.CrdRepository;
import org.bridge.base.service.CrudService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Qualifier("roleService")
@Service
public class RoleService extends CrudService<Role, RoleDto, Long> {
    public RoleService(CrdRepository<Role, Long> repository) {
        super(repository);
    }

    @Override
    @Qualifier("roleRepository")
    public void setRepository(CrdRepository crdRepository) {
        this.repository = crdRepository;
    }

    @Override
    public Class<?> getDtoClazz() {
        return RoleDto.class;
    }
}
