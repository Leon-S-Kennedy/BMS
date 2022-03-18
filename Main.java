package com.bowen.library;

import com.bowen.library.book.BookStorage;
import com.bowen.library.command.IExecutable;
import com.bowen.library.input.Input;
import com.bowen.library.input.QuitException;
import com.bowen.library.user.AdminUser;
import com.bowen.library.user.User;
import com.bowen.library.user.UserStorage;

public class Main {
    public static void main(String[] args) {

        //0.实例化一个负责处理输入的对象
        Input input=new Input();
        //改成从文件中来加载数据
        BookStorage bookStorage = BookStorage.loadFromFile();

        try {
            //1.用户登录
            //1.1需要一个用户管理对象完成登录的具体操作
            UserStorage useerStorage=new UserStorage();
            //1.2判断用户角色
            User user=useerStorage.login(input);
/*
            System.out.println(user.getUsername());
            if(user instanceof AdminUser){
                System.out.println(1);
            }else{
                System.out.println(0);
            }
*/

            //2.进入循环
            while(true){
                //2.1打印用户的菜单，斌且让用户选择
                //用一个接口来体现这种能力
                IExecutable command=input.menuAndSelect(user);
                
                command.execute(bookStorage,user,input);

            }


        }catch (QuitException exc){

        }
        //3.用户退出
        System.out.println("欢迎下次使用！！！");
    }
}
