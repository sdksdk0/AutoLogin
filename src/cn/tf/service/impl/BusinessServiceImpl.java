package cn.tf.service.impl;

import cn.tf.dao.UserDao;
import cn.tf.dao.UserDaoImpl;
import cn.tf.domain.User;
import cn.tf.service.BusinessService;
import cn.tf.util.SecurityUtil;

public class BusinessServiceImpl implements BusinessService {

	private UserDao userDao = new UserDaoImpl();
	public User login(String username, String password) {
		return userDao.find(username,password);
	}

}
