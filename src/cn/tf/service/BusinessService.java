package cn.tf.service;

import cn.tf.domain.User;

public interface  BusinessService {
	
	//利用用户名和密码登陆
	User login(String username,String password);
	
	

}
