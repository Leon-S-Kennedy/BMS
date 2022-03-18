package com.bowen.library.user;

import com.bowen.library.command.*;

public class AdminUser extends User {
    public AdminUser(String username) {
        super(username);
    }

    @Override
    public IExecutable[] getSupportedComands() {
        //此操作是为了得到IExecutable[]列表，其中每个元素为实现了IExecutable的类。
        return new IExecutable[]{
                new AddBookCommand(),
                new RemoveBookCommand(),
                new ListBookOrderByNamecommand(),
                new ListBookOrderByPriceCommand(),
                new ListBookOrderByBorrowedCommand(),
                new Quite()
        };
    }
}
