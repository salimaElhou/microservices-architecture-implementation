package elhou.salima.billingservice.web;

import elhou.salima.billingservice.entities.Bill;
import elhou.salima.billingservice.feign.CustomerRestClient;
import elhou.salima.billingservice.feign.ProductItemRestClient;
import elhou.salima.billingservice.model.Customer;
import elhou.salima.billingservice.model.Product;
import elhou.salima.billingservice.repository.BillRepository;
import elhou.salima.billingservice.repository.ProductItemRepository;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingRestController {
    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;

    private CustomerRestClient customerRestClient;
    private ProductItemRestClient productItemRestClient;

    public BillingRestController(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerRestClient customerRestClient, ProductItemRestClient productItemRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.productItemRestClient = productItemRestClient;
    }

    //
    @GetMapping(path="/fullBill/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id){
        //recuper la facture mn BD
        Bill bill= billRepository.findById(id).get();

        //recuper client a partir d un autre micro-service via REST
        Customer customer= customerRestClient.getCustomerById(bill.getCustomerID());
        bill.setCustomer(customer);

        //recuper les Details de chaque produit
        bill.getProductItems().forEach(pi->{
            Product product=productItemRestClient.getProductById(pi.getProductID());
           // pi.setProduct(product);
            pi.setProductName(product.getName());
        });
        return bill;

    }
}
