package com.example.Micro_Service.dao.repository;

import com.example.Micro_Service.dao.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
