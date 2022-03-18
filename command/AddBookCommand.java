package com.bowen.library.command;

import com.bowen.library.book.Book;
import com.bowen.library.book.BookStorage;
import com.bowen.library.input.Input;
import com.bowen.library.user.User;

public class AddBookCommand implements IExecutable{
    @Override
    public void execute(BookStorage bookStorage, User user, Input input) {
        System.out.println();
        System.out.println("添加书籍的操作,请添加书籍的信息");

        System.out.println();
        String name = input.prompt("请输入书籍的名称");
        System.out.println();
        String author = input.prompt("请输入书籍的作者名称");
        System.out.println();
        int price;
        while(true){
            try{
                String priceStr = input.prompt("请输入书籍的价格（请输入数字）");
                price=Integer.parseInt(priceStr);
                break;

            }catch (NumberFormatException exc){
                System.out.println("请正确输入！！！");
            }
        }
        System.out.println();
        String type = input.prompt("请输入书籍的类型");

        //将书籍信息记录下来
        Book book=new Book();
        book.name=name;
        book.author=author;
        book.price=price;
        book.type=type;
        book.BorrowedBy=null;

        //将书籍添加到书架上
        bookStorage.add(book);

        System.out.println();
        System.out.println(user.getUsername()+"完成添加书籍的操作");
        System.out.println();
    }

    @Override
    public String getName() {
        return "添加书籍";
    }
}
