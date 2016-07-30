package cn.tf.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.tf.domain.User;
import cn.tf.util.C3P0Util;

public class UserDaoImpl implements UserDao {

	private QueryRunner qr=new QueryRunner(C3P0Util.getDataSource());
	
	public User find(String username, String password) {
		try {
			return qr.query("select * from users3 where username=? and pwd=?", new BeanHandler<User>(User.class), username,password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
