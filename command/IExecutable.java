package com.bowen.library.command;

import com.bowen.library.book.BookStorage;
import com.bowen.library.input.Input;
import com.bowen.library.user.User;

public interface IExecutable {
        void execute(BookStorage bookStorage, User user, Input input);

        String getName();
}
