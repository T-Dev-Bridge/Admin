package com.bridge.admin.client;

import com.bridge.admin.error.FeignCircuitBreakerErrorDecoder;
import org.bridge.base.api.CommonResponseDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-service", configuration = FeignCircuitBreakerErrorDecoder.class, url = "${auth-service-url}")
@Qualifier("AuthHttpClient")
public interface AuthClient extends HttpClient{
    @PostMapping("/api/no-auth/encode")
    CommonResponseDto<String> encodePassword(@RequestBody String rawPassword);
}
