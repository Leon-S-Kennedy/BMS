package com.bowen.library.command;

import com.bowen.library.book.Book;
import com.bowen.library.book.BookStorage;
import com.bowen.library.input.Input;
import com.bowen.library.user.User;

public class RemoveBookCommand implements IExecutable{
    @Override
    public void execute(BookStorage bookStorage, User user, Input input) {
        System.out.println();
        System.out.println("开始删除书籍，请输入书籍信息");


        String name=input.prompt("请输入书籍名称");

        Book book=bookStorage.searchByName(name);

        if(book==null){
            System.out.println("没有找到这本书 《"+name+"》，无法删除" );
            return;
        }

        if(book.isBorrowed()){
            System.out.println("书籍已经被"+book.BorrowedBy+"借走，暂时不允许删除");
            return;
        }

        bookStorage.remove(book);

        System.out.println();
        System.out.println(user.getUsername()+"完成了删除《"+name+"》的操作");
        System.out.println();
    }

    @Override
    public String getName() {
        return "删除书籍";
    }
}
