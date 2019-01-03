package com.entity.service;

import java.util.List;

import com.entity.domain.User;

public interface IUserService {
	public User getUserById(int userId);
	public User getUserByNameAndPw(User user);
	public List<User> getUserList();
	public void save(User user);
}
