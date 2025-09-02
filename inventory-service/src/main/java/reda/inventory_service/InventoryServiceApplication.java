package reda.inventory_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import reda.inventory_service.entities.Product;
import reda.inventory_service.repo.ProductRepo;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(ProductRepo productRepo,
										RepositoryRestConfiguration configuration){
		return args -> {
			configuration.exposeIdsFor(Product.class);
			for (int i = 1; i < 10 ; i++) {
				productRepo.saveAll(List.of(
						Product.builder()
								.name("Computer "+i)
								.price(10000+Math.random()*10000)
								.quantity(1 + new Random().nextInt(200))
								.build()
				));
			}
		};
	}

}
