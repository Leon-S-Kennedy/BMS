package com.bowen.library.command;

import com.bowen.library.book.BookStorage;
import com.bowen.library.input.Input;
import com.bowen.library.input.QuitException;
import com.bowen.library.user.User;

public class Quite implements IExecutable{
    @Override
    public void execute(BookStorage bookStorage, User user, Input input) {
        throw new QuitException();
    }

    @Override
    public String getName() {
        return "退出系统";
    }
}
