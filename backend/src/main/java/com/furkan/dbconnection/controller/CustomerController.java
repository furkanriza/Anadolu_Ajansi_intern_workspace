package com.furkan.dbconnection.controller;

import com.furkan.dbconnection.model.Customer;
import com.furkan.dbconnection.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    //select all
    @GetMapping("/")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        System.out.println("customer list prepared");
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    //select by id
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isPresent()) {
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //insert
    @GetMapping("/insert")
    public ResponseEntity<Customer> insertCustomerByGet(@RequestParam List l) {
        Customer customer = new Customer(l.get(0).toString(),l.get(1).toString(),Double.valueOf(l.get(2).toString()),l.get(3).toString(),l.get(4).toString());
        Customer createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }


    //update
    @GetMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomerByGet(@PathVariable Long id, @RequestParam List<String> l) {
        Optional<Customer> optionalCustomer = customerService.getCustomerById(id);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();

            //name
            if (l.get(0).equals("")){
                existingCustomer.setName(existingCustomer.getName());
            }
            else{
                existingCustomer.setName(l.get(0));
            }
            //surname
            if (l.get(1).equals("")){
                existingCustomer.setSurname(existingCustomer.getSurname());
            }
            else{
                existingCustomer.setSurname(l.get(1));
            }
            //debt
            if (l.get(2).equals("")){
                existingCustomer.setDebt(existingCustomer.getDebt());
            }
            else{
                existingCustomer.setDebt(Double.parseDouble(l.get(2)));
            }
            //email
            if (l.get(3).equals("")){
                existingCustomer.setEmail(existingCustomer.getEmail());
            }
            else{
                existingCustomer.setEmail(l.get(3));
            }
            //phone
            if (l.get(4).equals("")){
                existingCustomer.setPhone(existingCustomer.getPhone());
            }
            else{
                existingCustomer.setPhone(l.get(4));
            }

            // Save the updated customer in the database
            Customer updatedCustomer = customerService.updateCustomer(id, existingCustomer);

            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //delete
    @GetMapping("/delete/{id}")
    public ResponseEntity<Customer> deleteCustomerByGet(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        System.out.println("here I am");
        Customer createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Customer updated = customerService.updateCustomer(id, updatedCustomer);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Optional<Customer> optionalCustomer = customerService.getCustomerById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            // Apply the updates to the customer object
            if (updates.containsKey("name")) {
                customer.setName((String) updates.get("name"));
            }
            if (updates.containsKey("surname")) {
                customer.setSurname((String) updates.get("surname"));
            }
            if (updates.containsKey("debt")) {
                customer.setDebt((Double) updates.get("debt"));
            }
            if (updates.containsKey("email")) {
                customer.setSurname((String) updates.get("email"));
            }
            if (updates.containsKey("phone")) {
                customer.setSurname((String) updates.get("phone"));
            }

            Customer updatedCustomer = customerService.updateCustomer(id,customer);

            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}



