package reda.order_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import reda.order_service.entities.ProductItems;

@RepositoryRestResource
public interface ProductItemsRepository extends JpaRepository<ProductItems,Long> {
}
