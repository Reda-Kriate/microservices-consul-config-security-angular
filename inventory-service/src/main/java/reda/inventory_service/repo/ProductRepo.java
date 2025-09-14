package reda.inventory_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import reda.inventory_service.entities.Product;

@RepositoryRestResource
public interface ProductRepo extends JpaRepository<Product,Long> {
}
