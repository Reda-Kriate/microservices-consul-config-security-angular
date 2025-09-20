package reda.order_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reda.order_service.enums.OrderStatus;
import reda.order_service.models.Customer;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders-tbl")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    private OrderStatus status;
    private Long customerId;
    private double total;
    @OneToMany(mappedBy = "order")
    private List<ProductItems> productItems;
    @Transient
    private Customer customer;
    
    public double getTotal(){
        double somme = 0;
        for (ProductItems pi:productItems) {
            somme+=pi.getAmount();
        }
        return somme;
    }
}
