package cn.itcast.dao;

import java.util.List;

import cn.itcast.pojo.User;

public interface UserDao {

	public User findUserById(int id);
	
	public List<User> findUserByUserName(String name);
}
