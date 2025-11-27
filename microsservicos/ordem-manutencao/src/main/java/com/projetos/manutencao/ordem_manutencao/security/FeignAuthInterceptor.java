package com.projetos.manutencao.ordem_manutencao.security;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignAuthInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        String token = TokenContext.getToken();
        if (token != null) {
            template.header("Authorization", "Bearer " + token);
        }
    }
}
