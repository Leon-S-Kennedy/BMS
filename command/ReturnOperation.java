package com.bowen.library.command;

import com.bowen.library.book.Book;
import com.bowen.library.book.BookStorage;
import com.bowen.library.input.Input;
import com.bowen.library.user.User;

public class ReturnOperation implements IExecutable{
    @Override
    public void execute(BookStorage bookStorage, User user, Input input) {
        System.out.println();
        System.out.println("开始归还书籍，请添加书籍信息：");

        String name=input.prompt("请输入书籍名称");
        Book book=bookStorage.searchByName(name);

        if(book==null){
            System.out.println("没有找到这本书 《"+name+"》，无法归还" );
            return;
        }

        if(!book.isBorrowed()){
            System.out.println("书籍没有被借走，不用归还");
            return;
        }
        if(!book.isBorrowedBy(user)){
            System.out.println("书籍不是被"+user.getUsername()+"借走的，无法归还");
            return;
        }

        book.returnBook(user);

        bookStorage.saveToFile();

        System.out.println();
        System.out.println(user.getUsername()+"完成了归还《"+name+"》的操作");
        System.out.println();

    }

    @Override
    public String getName() {
        return "归还书籍";
    }
}
