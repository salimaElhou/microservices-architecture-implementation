package elhou.salima.billingservice.model;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private  String price;
    private double quantity;

}
