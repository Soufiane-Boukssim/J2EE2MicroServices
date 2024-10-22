package com.example.Micro_Service;

import com.example.Micro_Service.dao.entity.Customer;
import com.example.Micro_Service.dao.repository.CustomerRepository;
import com.example.Micro_Service.web.CustomerRestController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository, CustomerRestController customerRestController) {
		return args -> {
			customerRepository.save(Customer.builder().name("X").email("X@gmail.com").build());
			customerRepository.save(Customer.builder().name("Y").email("Y@gmail.com").build());

			// Appeler les méthodes du contrôleur
			List<Customer> customers = customerRestController.customerList();
			System.out.println("Liste des clients : " + customers);

			Long id=1L;
			Customer customer = customerRestController.customerById(id);
			System.out.println("Le client numéros : "+id+" est "+customer);

			Customer newCustomer = new Customer();
			newCustomer.setName("Alice");
			newCustomer.setEmail("alice@example.com");
			Customer savedCustomer = customerRestController.saveCustomer(newCustomer);
			System.out.println("Client ajouté : " + savedCustomer);

		};
	}
}