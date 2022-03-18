package com.bowen.library.command;

import com.bowen.library.book.Book;
import com.bowen.library.book.BookStorage;
import com.bowen.library.input.Input;
import com.bowen.library.user.User;

import java.util.Arrays;
import java.util.Comparator;


public abstract class AbsListBookCommand implements IExecutable{
    protected abstract Comparator getComparator();

    @Override
    public void execute(BookStorage bookStorage, User user, Input input) {
        Comparator comparator=getComparator();
        Book[] books=bookStorage.toArray();
        if(comparator==null){
            Arrays.sort(books);
        }else{
            Arrays.sort(books,comparator);
        }
        for (Book book : books) {
            System.out.println(book.toString());
        }
        System.out.println();
        System.out.println("排序完成");
        System.out.println();
    }
}
