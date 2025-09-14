package reda.order_service.feignApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reda.order_service.models.Customer;

import java.util.List;

@FeignClient(name = "customer-service")
public interface CustomerRestFeign {

    @GetMapping("/customers")
    public PagedModel<Customer> findAllCustomer();

    @GetMapping("/customers/{id}")
    public Customer findCustomerById(@PathVariable Long id);
}
