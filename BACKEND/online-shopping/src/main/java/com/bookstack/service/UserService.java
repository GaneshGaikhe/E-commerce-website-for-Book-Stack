package com.bookstack.service;

import java.util.List;

import com.bookstack.dto.AddUserRequest;
import com.bookstack.dto.UserLoginRequest;
import com.bookstack.model.User;

public interface UserService {
	
	User registerUser(AddUserRequest userRequest);

    void deleteUserById(int userId);

    User loginUser(UserLoginRequest loginRequest);

    List<User> getAllUsers();

    List<User> getAllDeliveryPersons();

}
