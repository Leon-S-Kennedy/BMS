package com.bowen.library.user;

import com.bowen.library.input.Input;

public class UserStorage {
    private static final String[] ADMIN ={
            "libowen"
    };

    public User login(Input input) {
        //1.先让用户输入用户名
        String username=input.prompt("请输入用户名");
        //2.根据用户名判断是管理员还是普通用户
        //3.根据不同的用户创建不同的用户
        if(isAdmin(username)){
            return new AdminUser(username);
        }else{
            return new CommonUser(username);
        }

    }

    private boolean isAdmin(String username) {
        for (String s : ADMIN) {
            if(s.equals(username)){
                return true;
            }
        }
        return false;
    }
}
