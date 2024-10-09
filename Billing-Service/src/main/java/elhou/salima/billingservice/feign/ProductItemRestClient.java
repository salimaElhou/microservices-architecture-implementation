package elhou.salima.billingservice.feign;


import elhou.salima.billingservice.model.Customer;
import elhou.salima.billingservice.model.Product;
import jakarta.ws.rs.QueryParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductItemRestClient {

    @GetMapping(path="/products")
    PagedModel<Product> pageProducts();

    @GetMapping(path = "/products/{id}")
    Product getProductById(@PathVariable(name = "id") Long id);
}
