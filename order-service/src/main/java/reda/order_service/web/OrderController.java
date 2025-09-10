package reda.order_service.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reda.order_service.entities.Order;
import reda.order_service.entities.ProductItems;
import reda.order_service.feignApi.CustomerRestFeign;
import reda.order_service.feignApi.ProductRestFeign;
import reda.order_service.models.Customer;
import reda.order_service.models.Product;
import reda.order_service.repositories.OrderRepository;
import reda.order_service.repositories.ProductItemsRepository;

import java.util.List;

@RestController
public class OrderController {
    private OrderRepository orderRepository;
    private ProductItemsRepository productItemsRepository;
    private CustomerRestFeign customerRestFeign;
    private ProductRestFeign productRestFeign;

    public OrderController(OrderRepository orderRepository,
                           ProductItemsRepository productItemsRepository,
                           CustomerRestFeign customerRestFeign,
                           ProductRestFeign productRestFeign) {
        this.orderRepository = orderRepository;
        this.productItemsRepository = productItemsRepository;
        this.customerRestFeign = customerRestFeign;
        this.productRestFeign = productRestFeign;
    }

    @GetMapping("/fullOrders")
    public List<Order> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
            orders.forEach(order -> {
                Customer customerById = customerRestFeign.findCustomerById(order.getCustomerId());
                order.setCustomer(customerById);
            });
            List<ProductItems> productItems = productItemsRepository.findAll();
            productItems.forEach(p->{
                Product product = productRestFeign.findProductById(p.getProductId());
                p.setProduct(product);
            });
            List<Order> ordersFinal = orderRepository.findAll();
        return ordersFinal;
    }

    @GetMapping("/fullOrders/{id}")
    public Order orderById(@PathVariable Long id){
        Order order = orderRepository.findById(id).get();
        Customer customerById = customerRestFeign.findCustomerById(order.getCustomerId());
        order.setCustomer(customerById);
        order.getProductItems().forEach(p->{
            ProductItems productItems = productItemsRepository.findById(id).get();
            Product productById = productRestFeign.findProductById(productItems.getProductId());
            p.setProduct(productById);
        });
        return orderRepository.findById(id).get();
    }
}
