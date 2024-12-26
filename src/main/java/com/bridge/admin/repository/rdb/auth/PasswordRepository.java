package com.bridge.admin.repository.rdb.auth;

import org.bridge.base.repository.rdb.CrudRepository;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("passwordRepository")
public interface PasswordRepository extends CrudRepository<Password, Long> {
}
