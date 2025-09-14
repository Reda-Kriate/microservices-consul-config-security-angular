package org.reda.billing.web;

import org.reda.billing.config.ConsulConfig;
import org.reda.billing.config.VaultConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@RefreshScope
public class ConsulConfigRestController {
//    @Value("${token.accessToken}")
//    private Long accessToken;
//    @Value("${token.refreshToken}")
//    private Long refreshToken;
    @Autowired
    private ConsulConfig consulConfig;
    @Autowired
    private VaultConfig vaultConfig;
    @GetMapping("/config")
    public Map<String, Object> getConsulConfig(){
        return Map.of("Consul-config",consulConfig,"Vault-config",vaultConfig);
    }

}
