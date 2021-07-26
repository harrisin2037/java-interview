package com.example.demo.interview.service;

import java.util.List;

import com.example.demo.interview.model.User;

public interface UserService {
	List<User> getAllUsers();
	User insertUser(User user);
}
