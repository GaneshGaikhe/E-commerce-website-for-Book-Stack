package com.bookstack.usertest;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bookstack.dto.AddUserRequest;
import com.bookstack.model.User;
import com.bookstack.service.UserService;

@SpringBootTest
class OnlineShoppingApplicationTests {

@Autowired
    private UserService userService;

@Test
void contextLoads() {

AddUserRequest user= new AddUserRequest("Owner","Admin","admin@gmail.com","admin@123","9425167281","Admin","lal chowk","delhi",52384);
             
User newUser = userService.registerUser(user);
assertEquals(newUser.getLastName(), "Admin");

}

}