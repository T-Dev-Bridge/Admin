package com.bridge.admin.repository.rdb.auth;

import org.bridge.base.repository.rdb.CrudRepository;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("roleRepository")
public interface RoleRepository extends CrudRepository<Role, Long> {
}
