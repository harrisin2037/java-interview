package com.example.demo.interview.service;

import java.util.List;

import com.example.demo.interview.dao.UserDao;
import com.example.demo.interview.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	public List<User> getAllUsers() {
		List<User> users = userDao.getAllUsers();
		return users;
	}

	@Override
	public User insertUser(User user) {
		return userDao.insertUser(user);
	}

}