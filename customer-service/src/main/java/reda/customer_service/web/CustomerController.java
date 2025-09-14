package reda.customer_service.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class CustomerController {

    @Value("${customer.name}")
    private String nom;
    @Value("${customer.prenom}")
    private String prenom;
    @Value("${customer.spec:no-specialite}")
    private String spec;


    @GetMapping("/params")
    public Map<String,String> param(){
        return Map.of("nom",nom,"prenom",prenom,"spec",spec);
    }
}
