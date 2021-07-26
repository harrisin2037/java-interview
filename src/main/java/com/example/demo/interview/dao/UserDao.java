package com.example.demo.interview.dao;

import java.util.List;

import com.example.demo.interview.model.User;

public interface UserDao {
	List<User> getAllUsers();
	User insertUser(User user);
}
