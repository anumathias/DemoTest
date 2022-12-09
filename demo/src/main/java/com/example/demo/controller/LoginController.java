package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.LoginRepository;
import com.example.demo.model.Login;

@RestController
@RequestMapping
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;



  @Autowired
   private PasswordEncoder passwordEncoder;
       @PostMapping("/welcome")
       public Login getWelcome(@RequestBody Login login) {
           String username = login.getUsername();
           String password = login.getPassword();
           
           Login findByUsername = loginRepository.findByUsername(username);
           
           boolean status;

          if(findByUsername != null) {
               if(password.equals(findByUsername.getPassword())) {
                   status = true;
               }
               else {
                   status = false;
               }
           }
           else {
               status = false;
           }

          if(status) {
               //return "Login Successful";
               return findByUsername;
           }
           else {
               return null;
           }  
           
       }
}
