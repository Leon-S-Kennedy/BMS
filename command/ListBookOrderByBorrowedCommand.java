package com.bowen.library.command;

import com.bowen.library.book.Book;
import com.bowen.library.book.BookStorage;
import com.bowen.library.input.Input;
import com.bowen.library.user.User;

import java.util.Comparator;

public class ListBookOrderByBorrowedCommand extends AbsListBookCommand{
    private static class BorrowedComparator implements Comparator{

        @Override
        public int compare(Object o1, Object o2) {
            Book b1=(Book) o1;
            Book b2=(Book) o2;

            int borrowed1=b1.BorrowedBy==null ? 1:0;
            int borrowed2=b2.BorrowedBy==null ? 1:0;

            return borrowed2-borrowed1;

        }
    }

    private final Comparator comparator=new BorrowedComparator();

    //BorrowedComparator只使用了一次，可以用匿名类来实现。在此不作展示

    @Override
    protected Comparator getComparator() {
        return comparator;
    }

/*
    public void execute(BookStorage bookStorage, User user, Input input) {
        System.out.println();
        System.out.println("按借阅情况排序的操作");
        System.out.println();
        System.out.println("排序完成");
        System.out.println();
    }
*/

    @Override
    public String getName() {
        return "按借阅情况排序并列出";
    }
}
