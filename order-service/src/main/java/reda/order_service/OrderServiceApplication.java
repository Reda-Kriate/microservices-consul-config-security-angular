package reda.order_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import reda.order_service.entities.Order;
import reda.order_service.entities.ProductItems;
import reda.order_service.enums.OrderStatus;
import reda.order_service.feignApi.CustomerRestFeign;
import reda.order_service.feignApi.ProductRestFeign;
import reda.order_service.models.Customer;
import reda.order_service.models.Product;
import reda.order_service.repositories.OrderRepository;
import reda.order_service.repositories.ProductItemsRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
//	.stream().toList()
	@Bean
	CommandLineRunner runner(OrderRepository orderRepository,
										CustomerRestFeign customerRestFeign,
										ProductRestFeign productRestFeign,
										RepositoryRestConfiguration configuration){

		return args -> {
			configuration.exposeIdsFor(Order.class,ProductItems.class);
			List<Product> products = productRestFeign.findAllProducts().getContent();
			System.out.println(products);
			List<Customer> customers = customerRestFeign.findAllCustomer().getContent();
			customers.forEach(c-> {
				for (int i = 1; i < 6 ; i++) {
					Order order = Order.builder()
							.createdAt(new Date())
							.customerId(c.getId())
							.status(Math.random()>0? OrderStatus.CREATED :
									Math.random()>0.25? OrderStatus.PENDING :
											Math.random()>0.5? OrderStatus.DELIVERED :
													OrderStatus.CANCELED)
							.build();
					orderRepository.save(order);
				}
			});

		};
	}

}
