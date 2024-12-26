package com.bridge.admin.api.auth;

import com.bridge.admin.service.noauth.NoAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bridge.base.api.CommonResponseDto;
import org.bridge.base.exception.CommonException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRestController {
    private final NoAuthService noAuthService;

    @GetMapping("/test")
    public CommonResponseDto<String> test() throws CommonException, InterruptedException {
        return new CommonResponseDto<String>(true, noAuthService.hana("ccc", "1234"));
    }
}
