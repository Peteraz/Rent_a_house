package com.entity.IDao;

import java.util.List;

import com.entity.domain.User;

public interface IUserDao {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    public User getUserByNameAndPw(User user);
    
    public List<User> getUserList();
}