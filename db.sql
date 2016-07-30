--mysql数据库

create database autologin;
use autologin;

create table users3(
	id int primary key auto_increment,
	username varchar(40)  not null unique,
	pwd varchar(100) not null,
	nickname varchar(40) not null

);
SELECT *FROM users3;

--插入用户名为aa，密码为123的用户，密码已加密转换
INSERT INTO users3 VALUES(1,"aa","ICy5YqxZB1uWSwcVLSNLcA==","我自己");





