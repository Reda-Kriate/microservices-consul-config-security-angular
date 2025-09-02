package reda.order_service.feignApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.web.PagedModel;
import org.springframework.data.web.config.SpringDataJacksonConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reda.order_service.models.Product;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductRestFeign {
    @GetMapping("/products")
    public PagedModel<Product> findAllProducts();

    @GetMapping("/products/{id}")
    public Product findProductById(@PathVariable Long id);
}
