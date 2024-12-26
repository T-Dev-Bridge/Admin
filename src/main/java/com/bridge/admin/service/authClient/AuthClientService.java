package com.bridge.admin.service.authClient;

import com.bridge.admin.client.AuthClient;
import com.bridge.admin.exception.RecordException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.bridge.base.exception.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthClientService {

    @Autowired
    @Qualifier("AuthHttpClient")
    public void setClient(AuthClient AuthClient) {
        this.authClient = AuthClient;
    }

    protected AuthClient authClient;

    @CircuitBreaker(name = "basicCircuitBreakerConfig", fallbackMethod = "signupFallback")
    public String encodePassword(String password) {
        try {
            return authClient.encodePassword(password).getData();
        }catch (Exception e){
            throw new RecordException("인증 서버 오류 발생");
        }
    }

    // fallback 메소드는 기존 메서드와 반환 타입이 같아야 한다.
    private String signupFallback(Exception e) throws CommonException {
        log.error("[Auth Service Error] callFallback {}", e.getMessage());
        throw new CommonException(e.getMessage());
    }

    private String signupFallback(CallNotPermittedException e) throws CommonException {
        log.warn("[CircuitBreaker : OPEN] CircuitBreaker is Open .");
        throw new CommonException("인증 서버 오류 발생");
    }
}
