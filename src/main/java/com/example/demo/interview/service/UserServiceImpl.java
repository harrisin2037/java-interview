package com.example.demo.interview.service;

import java.util.List;

import com.example.demo.interview.dao.UserDao;
import com.example.demo.interview.dao.UserRedisDao;
import com.example.demo.interview.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	UserRedisDao userRedisDao;

	public List<User> getAllUsers() {
		List<User> users = userDao.getAllUsers();
		return users;
	}

	@Override
	public User insertUser(User user) {
		User record = userRedisDao.getUserByKeys(user.getId1(), user.getId2());
		if (record != null) {
			System.out.println(record);
			return record;
		}
		User result = userDao.insertUser(user);
		userRedisDao.insertUser(result);
		return result;
	}

}