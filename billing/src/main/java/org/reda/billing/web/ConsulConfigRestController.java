package org.reda.billing.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@RefreshScope
public class ConsulConfigRestController {
    @Value("${token.accessToken}")
    private Long accessToken;
    @Value("${token.refreshToken}")
    private Long refreshToken;

    @GetMapping("/config")
    public Map<String, Object> getConsulConfig(){
        return Map.of("accessToken",accessToken,"refreshToken",refreshToken);
    }

}
