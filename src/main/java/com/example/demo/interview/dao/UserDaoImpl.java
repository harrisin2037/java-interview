package com.example.demo.interview.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.example.demo.interview.model.User;
import com.example.demo.interview.model.UserRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

	// private Log log = LogFactory.getLog(getClass());

	@Autowired
	DataSource dataSource;

	// @Autowired
    // private RedisTemplate<String, User> redisTemplate;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public List<User> getAllUsers() {
		String sql = "SELECT * FROM user";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

		List<User> result = new ArrayList<User>();
		for (Map<String, Object> row : rows) {
			User user = new User();
			user.setId1((String) row.get("id1"));
			user.setId2((String) row.get("id2"));
			result.add(user);
		}

		return result;
	}

	// public User getUserById(Long id) {

	// 	final String key = "user_" + id;
    //     final ValueOperations<String, User> operations = redisTemplate.opsForValue();
    //     final boolean hasKey = redisTemplate.hasKey(key);

	// 	if (hasKey) {
    //         final User user = operations.get(key);
    //         log.info("UserServiceImpl.findById() : cache user >> " + user.toString());
    //         return user;
    //     }

	// 	String sql = "SELECT * FROM user WHERE id = ?";
	// 	final Optional<User> user = Optional.ofNullable(getJdbcTemplate().queryForObject(sql, new Object[]{id}, new UserRowMapper()));

	// 	if (user.isPresent()) {
    //         operations.set(key, user.get(), 10, TimeUnit.SECONDS);
    //         log.info("UserDaoImpl.getUserById() : cache insert >> " + user.get().toString());
    //         return user.get();
    //     } else {
    //         throw new ResourceNotFoundException();
    //     }
	// }

	@Override
	public User insertUser(User update) {

		User user = new User();

		user.initializeUUID();
		user.setId1(update.getId1());
		user.setId2(update.getId2());

		User record = null;	

		try {
			String sql = "SELECT * FROM user WHERE id1 = ? AND id2 = ?;";
			record = getJdbcTemplate().queryForObject(sql, new Object[]{user.getId1(), user.getId2()}, new UserRowMapper());	
		}
		catch (EmptyResultDataAccessException e) {
			String sql = "INSERT INTO user (userId, id1, id2) VALUES (?, ?, ?)";
			getJdbcTemplate().update(sql, new Object[]{
					user.getUserId().toString(), user.getId1(), user.getId2()
			});
			record = getJdbcTemplate().queryForObject("SELECT * FROM user WHERE id1 = ? AND id2 = ?", new Object[]{user.getId1(), user.getId2()}, new UserRowMapper());	
			return record;
		}

		return record;
	}

}
