package com.example.demo.interview.dao;

import com.example.demo.interview.model.User;

public interface UserRedisDao {
	boolean isUserExist(String id1, String id2);
	User getUserByKeys(String id1, String id2);
	User insertUser(User user);
}
