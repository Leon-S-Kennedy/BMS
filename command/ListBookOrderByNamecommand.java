package com.bowen.library.command;

import com.bowen.library.book.Book;
import com.bowen.library.book.BookStorage;
import com.bowen.library.input.Input;
import com.bowen.library.user.User;

import java.util.Arrays;

public class ListBookOrderByNamecommand implements IExecutable{
    @Override
    public void execute(BookStorage bookStorage, User user, Input input) {
        System.out.println();
/*
        1.从书架中拿出所有书籍信息
        2.排序
        3.打印
*/
        Book[] books =bookStorage.toArray();
        Arrays.sort(books);//由于没有传comparator所有默认以实现了Comparable来进行的
        for (Book book : books) {
            System.out.printf("书名：《%s》 作者：%s 价格：%d 类型：%s ",book.name,book.author,book.price,book.type);
            if(book.BorrowedBy==null){
                System.out.println("没有被借走");
            }else{
                System.out.printf("被 %s 借走了\n",book.BorrowedBy);
            }
        }

        System.out.println();
        System.out.println("排序完成");
        System.out.println();

    }

    @Override
    public String getName() {
        return "按名称排序并列出";
    }
}
