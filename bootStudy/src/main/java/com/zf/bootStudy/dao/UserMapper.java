package com.zf.bootStudy.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.zf.bootStudy.entity.User;

@Mapper
public interface UserMapper {
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	@Insert("insert into user(phone,password) values(#{phone},#{password})")
	void save(User user);

	@Select("select * from user where phone = #{phone} and password = #{password}")
	User findUserByPhoneAndPassword(User user);

	@Select("select * from user where phone = #{phone}")
	User findUserByPhone(String phone);

}
