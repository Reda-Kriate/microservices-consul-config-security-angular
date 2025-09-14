package org.reda.billing.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "token")
@Component
@Data
public class ConsulConfig {
    private Long accessToken;
    private Long refreshToken;
}
