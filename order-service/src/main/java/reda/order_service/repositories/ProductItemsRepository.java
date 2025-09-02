package reda.order_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import reda.order_service.models.Product;

@RepositoryRestResource
public interface ProductItemsRepository extends JpaRepository<Product,Long> {
}
