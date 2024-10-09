package elhou.salima.billingservice;

import elhou.salima.billingservice.entities.Bill;
import elhou.salima.billingservice.entities.ProductItem;
import elhou.salima.billingservice.feign.CustomerRestClient;
import elhou.salima.billingservice.feign.ProductItemRestClient;
import elhou.salima.billingservice.model.Customer;
import elhou.salima.billingservice.model.Product;
import elhou.salima.billingservice.repository.BillRepository;
import elhou.salima.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerRestClient customerRestClient, ProductItemRestClient productItemRestClient){
		return args -> {
		Customer customer = customerRestClient.getCustomerById(1L);
		Bill  bill=	billRepository.save(new Bill(null,new Date(),null,customer.getId(),null));

		PagedModel<Product> productPageModel =productItemRestClient.pageProducts();
		productPageModel.forEach(p->{
			ProductItem productItem = new ProductItem();
			productItem.setPrice(Double.parseDouble(p.getPrice()));
			productItem.setQuantity(1+new Random().nextInt(100));
			productItem.setBill(bill);//associe product -> facteur -bill
			productItem.setProductID(p.getId());
			productItemRepository.save(productItem);

		});

		//System.out.println("-----------------------TEST------------------");
			//System.out.println(customer.getId());
			//System.out.println(customer.getName());
			//System.out.println(customer.getEmail());

		};
	}

}
