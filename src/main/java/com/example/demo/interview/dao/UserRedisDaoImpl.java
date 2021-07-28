package com.example.demo.interview.dao;

import javax.annotation.PostConstruct;

import com.example.demo.interview.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class UserRedisDaoImpl implements UserRedisDao {

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    private ValueOperations<String, User> valueOperations;

	@Autowired
	public void UserRedisDao(RedisTemplate<String, User> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	private void intializeOperations() {
        this.valueOperations = redisTemplate.opsForValue();
	}

    @Override
	public User getUserByKeys(String id1, String id2) {
        User user = new User();
        user.setId1(id1);
        user.setId2(id2);
        final boolean exist = redisTemplate.hasKey(user.toHashKey());
        if (exist) {
            System.out.println("exist " + exist);
            return valueOperations.get(user.toHashKey());
        }
        return null;
    };

	@Override
	public User insertUser(User user) {
        final boolean exist = redisTemplate.hasKey(user.toHashKey());
        if (exist) {
            redisTemplate.delete(user.toHashKey());
        }
        valueOperations.set(user.toHashKey(), user);
        return user;
    };

    @Override
    public boolean isUserExist(String id1, String id2) {
        User user = new User();
        user.setId1(id1);
        user.setId2(id2);
        return redisTemplate.hasKey(user.toHashKey());
    }

}
