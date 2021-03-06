package com.entity.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.IDao.IUserDao;
import com.entity.domain.User;
import com.entity.service.IUserService;  
  
@Service("userService")  
public class UserServiceImpl implements IUserService {  
    @Resource  
    private IUserDao userDao;  
    public User getUserById(int userId) {  
        // TODO Auto-generated method stub  
        return this.userDao.selectByPrimaryKey(userId);  
    }
    public User getUserByNameAndPw(User user){
    	return userDao.getUserByNameAndPw(user);
    }
    public List<User> getUserList(){
    	return userDao.getUserList();
    }
    public void save(User user){
    	userDao.insertSelective(user);
    }
}