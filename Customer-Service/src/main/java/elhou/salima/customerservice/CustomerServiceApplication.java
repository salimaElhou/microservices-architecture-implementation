package elhou.salima.customerservice;


import elhou.salima.customerservice.Repository.CustomerRepository;
import elhou.salima.customerservice.entities.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(Customer.class);
        return args -> {
                customerRepository.save(new Customer(null,"salima","salimaelhou@gmail.com"));
            customerRepository.save(new Customer(null,"salma","salmaezaccani@gmail.com"));
            customerRepository.save(new Customer(null,"assia","assiaaitjeddi@gmail.com"));
            customerRepository.findAll().forEach(c->{
                System.out.println(c.toString());
            });
        };
    }

}
