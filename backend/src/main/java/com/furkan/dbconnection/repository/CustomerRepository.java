package com.furkan.dbconnection.repository;

import com.furkan.dbconnection.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Additional custom query methods can be defined here if needed
}
