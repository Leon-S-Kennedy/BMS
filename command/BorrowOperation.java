package com.bowen.library.command;

import com.bowen.library.book.Book;
import com.bowen.library.book.BookStorage;
import com.bowen.library.input.Input;
import com.bowen.library.user.User;

public class BorrowOperation implements IExecutable{
    @Override
    public void execute(BookStorage bookStorage, User user, Input input) {
        System.out.println();
        System.out.println("开始借阅书籍，请添加书籍信息：");

        String name=input.prompt("请输入书籍名称");
        Book book=bookStorage.searchByName(name);

        if(book==null){
            System.out.println("没有找到这本书 《"+name+"》，无法借阅" );
            return;
        }

        if(book.isBorrowed()){
            System.out.println("书籍已经被"+book.BorrowedBy+"借走，暂时不允许借阅");
            return;
        }
        //由于book返回的引用指向书籍对象，和我们之前BookStorage中的是同一个对象(浅拷贝)
        //所以也会对BookStorage产生效果
        book.borrow(user);

        bookStorage.saveToFile();

        System.out.println();
        System.out.println(user.getUsername()+"完成了借阅《"+name+"》的操作");
        System.out.println();

    }

    @Override
    public String getName() {
        return "借阅书籍";
    }
}
