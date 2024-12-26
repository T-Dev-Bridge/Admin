package com.bridge.admin.repository.rdb.auth;

import org.bridge.base.repository.rdb.CrdRepository;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

@Qualifier("managerRepository")
public interface ManagerRepository extends CrdRepository<Manager, String> {

    Optional<Manager> findByUsername(String managerId);
}
