package com.example.Micro_Service.web;

import com.example.Micro_Service.dao.entity.Customer;
import com.example.Micro_Service.dao.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController()
public class CustomerRestController {

    @Autowired
    private CustomerRepository customerRepository;

    //2eme maniere
    @GetMapping
    //
    public List<Customer> customerList() {
        return customerRepository.findAll();
    }


    //2eme maniere
    @GetMapping("{id}")
    //
    public Customer customerById(@PathVariable Long id) {
        return customerRepository.findById(id).orElse(null); // Retourne le client ou null si non trouvé
    }

    //2eme maniere
    @PostMapping
    //
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer); // Sauvegarde le client
    }

}
