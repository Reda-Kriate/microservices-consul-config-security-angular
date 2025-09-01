package reda.customer_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import reda.customer_service.entities.Customer;

@RepositoryRestResource
public interface CustomerRepo extends JpaRepository<Customer,Long> {
}
