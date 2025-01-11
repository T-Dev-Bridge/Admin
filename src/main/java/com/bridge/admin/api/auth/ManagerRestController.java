package com.bridge.admin.api.auth;

import com.bridge.admin.repository.rdb.auth.Manager;
import com.bridge.admin.service.auth.ManagerDto;
import com.bridge.admin.service.auth.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bridge.base.api.CommonResponseDto;
import org.bridge.base.api.CrudRestController;
import org.bridge.base.exception.CommonException;
import org.bridge.base.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/manager")
@Tag(name = "관리자", description = "관리자 관리")
public class ManagerRestController extends CrudRestController<Manager, ManagerDto, String> {

    private final ManagerService managerService;

    @Override
    @Autowired
    @Qualifier("managerService")
    protected void setService(CrudService<Manager, ManagerDto, String> service) {
        this.name = "관리자";
        this.service = service;
    }

    @Operation(summary = "관리자 ID 조회", description = "관리자 ID 조회")
    @GetMapping("/profile/{managerId}")
    public CommonResponseDto<ManagerDto> getManagerById(@PathVariable String managerId) {
        return new CommonResponseDto<ManagerDto>(true, ((ManagerService) service).getManager(managerId));
    }

    @Operation(summary = "관리자 회원 가입", description = "관리자 회원가입")
    @PostMapping("/signup")
    public CommonResponseDto<ManagerDto> signupManager(
            @RequestBody ManagerDto dto, @RequestHeader("X-Authenticated-Username") String userId
    ) throws CommonException {
        return new CommonResponseDto<ManagerDto>(true, managerService.saveWithPassword(dto, userId));
    }
}
