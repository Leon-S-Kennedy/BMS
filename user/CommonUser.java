package com.bowen.library.user;

import com.bowen.library.command.*;

public class CommonUser extends User {
    public CommonUser(String username) {
        super(username);
    }

    @Override
    public IExecutable[] getSupportedComands() {
        return new IExecutable[]{
                new ListBookOrderByNamecommand(),
                new ListBookOrderByPriceCommand(),
                new ListBookOrderByBorrowedCommand(),
                new BorrowOperation(),
                new ReturnOperation(),
                new Quite()
        };
    }
}
