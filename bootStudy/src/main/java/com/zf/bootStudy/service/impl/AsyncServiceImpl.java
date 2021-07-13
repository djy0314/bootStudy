package com.zf.bootStudy.service.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.zf.bootStudy.dao.UserMapper;
import com.zf.bootStudy.entity.User;
import com.zf.bootStudy.service.AsyncService;
@Service
public class AsyncServiceImpl implements AsyncService {

	private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);
	@Autowired
	private UserMapper userMapper;
	
    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync() {
        logger.info("start executeAsync");

        System.out.println("异步线程要做的事情");
        System.out.println("可以在这里执行批量插入等耗时的事情");
        User user =new User();
        user.setPassword("123");
        user.setPhone(Math.random()+"");
        userMapper.save(user);
        logger.info("end executeAsync");
    }

}
