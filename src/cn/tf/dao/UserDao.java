package cn.tf.dao;

import cn.tf.domain.User;

public interface UserDao {

	User find(String username, String password);

}
