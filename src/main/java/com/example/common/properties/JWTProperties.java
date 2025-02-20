package com.example.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "admin.jwt")
@Data
public class JWTProperties {
    private String adminSecretKey;
    private long adminTTL;
    private String adminTokenName;
}
