package com.bridge.admin.api.auth;

import com.bridge.admin.service.auth.ManagerDto;
import com.bridge.admin.service.auth.ManagerService;
import com.bridge.admin.service.noauth.NoAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bridge.base.api.CommonResponseDto;
import org.bridge.base.exception.CommonException;
import org.springframework.web.bind.annotation.*;

/**
 * 인증 없이 사용 가능한 API 모음
 * /api/no-auth 경로로 통일하여 하나의 클래스에서 통합 관리
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/no-auth")
@Tag(name = "인증 없음", description = "인증 없이 사용 가능한 API")
public class NoAuthRestController {
    private final NoAuthService noAuthService;
    private final ManagerService managerService;

    @Operation(summary = "관리자 회원가입", description = "관리자 회원가입")
    @PostMapping(value = "/signup", produces = "application/json")
    public CommonResponseDto<?> signup(
            @RequestBody ManagerDto managerDto) throws CommonException {
        return new CommonResponseDto<>(true, noAuthService.signup(managerDto));
    }

    @GetMapping("/manager/{managerId}")
    public CommonResponseDto<String> getManagerById(@PathVariable("managerId") String managerId) {
        return new CommonResponseDto<String>(true, managerService.getManager(managerId).getId());
    }
}
