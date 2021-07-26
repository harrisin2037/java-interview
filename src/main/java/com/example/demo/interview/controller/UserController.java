package com.example.demo.interview.controller;

import java.util.List;

import com.example.demo.interview.model.User;
import com.example.demo.interview.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController extends AbstractRESTfulController  {

	protected static final String DEFAULT_PAGE_SIZE = "10";
    protected static final String DEFAULT_PAGE_NUM = "0";

	@Autowired
	UserService userService;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all users.", notes = "The list can be paginated by providing a page number with default 0 and a page size withdefault 10")
    public @ResponseBody List<User> getUsers() {
		return userService.getAllUsers();
	}

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Createuser", notes = "Create the new user")
	public String insertUser(@RequestBody User user) {
		return userService.insertUser(user).getUserId().toString();
	}

}
