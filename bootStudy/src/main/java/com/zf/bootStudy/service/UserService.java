package com.zf.bootStudy.service;

import com.zf.bootStudy.entity.User;

public interface UserService {

	void save(User user);

	boolean findUserByPhoneAndPassword(User user);

	boolean findUserByPhone(String phone);

}
