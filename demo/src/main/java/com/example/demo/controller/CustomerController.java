package com.example.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.CustomerRepository;
import com.example.demo.model.Customer;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
        
     @GetMapping
        public List<Customer> getAllFarmers()
        {
            return customerRepository.findAll();
        }



    
     //add new employee rest api
     @PostMapping("/register")
        public ResponseEntity<String> registerFarmer(@RequestBody 	Customer customer) {
    	 Customer savedCustomer = null;
            ResponseEntity response = null;
            try {
                String hashPwd=passwordEncoder.encode(customer.getPwd());
                customer.setPwd(hashPwd);
                savedCustomer = customerRepository.save(customer);
                if (savedCustomer.getId() > 0) {
                    response = ResponseEntity
                            .status(HttpStatus.CREATED)
                            .body("Given user details are successfully registered");
                }
            } catch (Exception ex) {
                response = ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("An exception occured due to " + ex.getMessage());
            }
            return response;
        }
//     
     //get employee by id rest api
     @GetMapping("/{id}")
     public ResponseEntity getbyid(@PathVariable("id") int id) {
            return new ResponseEntity(customerRepository.findById(id), HttpStatus.OK);
        }
//     
     //update employee
     @PutMapping("/{id}")
        public ResponseEntity updateEmployeebyid(@RequestBody Customer customer,@PathVariable("id") int id) {
            try {
            	Customer existfarmers= customerRepository.findById(id).get();
            	customerRepository.save(customer);
                return new ResponseEntity<>(customerRepository.findById(id),HttpStatus.OK);
            }catch (NoSuchElementException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }}
            
         
            @DeleteMapping("/{id}")
            public void deleteEmployee(@PathVariable("id") int id) {
                this.customerRepository.deleteById(id);
      }
}
