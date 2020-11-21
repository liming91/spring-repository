package com.ming.service.impl;

import com.ming.service.IUserService;

public class UserServiceImpl implements IUserService {
    @Override
    public String work(String userName) {
        System.out.println(userName+"正在工作中！！");
        return userName;
    }
}
