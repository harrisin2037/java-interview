package com.example.demo.interview.controller;

import com.example.demo.interview.exception.InvalidRequestException;
import com.example.demo.interview.model.User;
import com.example.demo.interview.service.UserService;

import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController extends AbstractRESTfulController {

	@Autowired
	UserService userService;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create user", notes = "Create the new user")
	public User insertUser(@RequestBody User user) {
        if (StringUtils.isBlank(user.getId1()) || StringUtils.isBlank(user.getId2())) {
			throw new InvalidRequestException("id1 or id2 is");
		}
		return userService.insertUser(user);
	}

}
