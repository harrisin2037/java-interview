package com.example.demo.interview.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user = new User();
        user.setId(UUID.fromString(rs.getString("id")));
        user.setId1(rs.getString("id1"));
        user.setId2(rs.getString("id2"));

        return user;

    }
}