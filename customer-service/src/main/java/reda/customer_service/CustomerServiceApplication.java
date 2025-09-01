package reda.customer_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import reda.customer_service.entities.Customer;
import reda.customer_service.repositories.CustomerRepo;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(CustomerRepo customerRepo,
										RepositoryRestConfiguration restConfiguration){
		return args -> {
			restConfiguration.exposeIdsFor(Customer.class);
			customerRepo.saveAll(List.of(
					Customer.builder().name("Reda").email("reda@gmail.com").build(),
					Customer.builder().name("Amine").email("amine@gmail.com").build(),
					Customer.builder().name("Younes").email("younes@gmail.com").build(),
					Customer.builder().name("Othmane").email("othmane@gmail.com").build()
			));
		};
	}

}
