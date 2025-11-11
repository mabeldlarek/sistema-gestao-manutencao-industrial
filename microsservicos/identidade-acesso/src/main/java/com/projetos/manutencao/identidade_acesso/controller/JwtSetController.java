package com.projetos.manutencao.identidade_acesso.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.interfaces.RSAPublicKey;
import java.util.Map;

@RestController
@RequestMapping("/.well-known")
public class JwtSetController {

    private final RSAPublicKey publicKey;

    public JwtSetController(@Value("${jwt.public.key}") RSAPublicKey publicKey) {
        this.publicKey = publicKey;
    }

    @GetMapping("/jwks.json")
    public Map<String, Object> keys() {
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }
}