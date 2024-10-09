package elhou.salima.billingservice.entities;


import elhou.salima.billingservice.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double quantity;
    private double price;
    private  long productID;
    @ManyToOne
    private Bill bill;
    @Transient
    private Product product;


}
