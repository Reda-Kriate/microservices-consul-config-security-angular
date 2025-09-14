package org.reda.billing.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "user")
@Component
@Data
public class VaultConfig {
    private String username;
    private String password;
    private String otp;
}
