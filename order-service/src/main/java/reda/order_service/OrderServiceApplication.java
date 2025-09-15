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
										ProductItemsRepository productItemsRepository,
										CustomerRestFeign customerRestFeign,
										ProductRestFeign productRestFeign,
										RepositoryRestConfiguration configuration){

		return args -> {
			configuration.exposeIdsFor(Order.class,ProductItems.class);
			List<Product> products = productRestFeign.findAllProducts().getContent().stream().toList();
			List<Customer> customers = customerRestFeign.findAllCustomer().getContent().stream().toList();
			Long customerId = 1L;
			Customer customer = customerRestFeign.findCustomerById(customerId);
			Random random = new Random();
			for (int i = 0; i < 20; i++) {
				Order order = Order.builder()
						.customerId(customers.get(random.nextInt(customers.size())).getId())
						.status(Math.random()>0.25 ? OrderStatus.CREATED :
								Math.random()>0.5 ? OrderStatus.CANCELED :
								Math.random()>0.75 ? OrderStatus.DELIVERED :
										OrderStatus.PENDING)
						.createdAt(new Date())
						.build();
				Order savedOrder = orderRepository.save(order);
				for (int j = 0; j < products.size(); j++) {
					ProductItems productItems = ProductItems.builder()
							.productId(products.get(j).getId())
							.order(savedOrder)
							.discount(Math.random())
							.quantity(1+ random.nextInt(10))
							.price(100+ random.nextInt(300)+Math.random()*1000)
							.build();
					productItemsRepository.save(productItems);
				}
			}

		};
	}

}
