package daisonp.postgres.containers.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import daisonp.postgres.containers.model.Customer;
import daisonp.postgres.containers.repository.CustomerRepository;

@RestController
public class CustomerController
{
    @Autowired
    private CustomerRepository repository;

    @GetMapping("/customers")
    public Page<Customer> getCustomerss(Pageable pageable) {
        return repository.findAll(pageable);
    }


    @PostMapping("/customers")
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return repository.save(customer);
    }

    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@PathVariable Long id,
                                   @Valid @RequestBody Customer request) {
        return repository.findById(id)
                .map(customer -> {
                    customer.setFirstName(request.getFirstName());
                    customer.setLastName(request.getLastName());
                    return repository.save(customer);
                })
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));  
    }


    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        return repository.findById(id)
                .map(customer -> {
                    repository.delete(customer);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
    }

}
