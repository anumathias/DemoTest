package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "users")
public class Login {
	 @Id
	    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
	    @GenericGenerator(name = "native",strategy = "native")
	    private int id;
	    private String username;
	    private String password;
	    public Login() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	    public Login(int id, String username, String password) {
	        super();
	        this.id = id;
	        this.username = username;
	        this.password = password;
	    }
	    public int getId() {
	        return id;
	    }
	    public void setId(int id) {
	        this.id = id;
	    }
	    public String getUsername() {
	        return username;
	    }
	    public void setUsername(String username) {
	        this.username = username;
	    }
	    public String getPassword() {
	        return password;
	    }
	    public void setPassword(String password) {
	        this.password = password;
	    }
	    @Override
	    public String toString() {
	        return "Login [id=" + id + ", username=" + username + ", password=" + password + "]";
	    }
	    
	    
	}

