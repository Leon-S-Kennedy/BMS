package com.bowen.library.book;

import com.bowen.library.user.User;

import java.util.Objects;

public class Book implements Comparable {
    public String name;
    public String author;
    public int price;
    public String type;
    public String BorrowedBy;

    @Override
    public int compareTo(Object o) {
        Book b=(Book) o;
        return name.compareTo(b.name);
    }

    public String toString1() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", BorrowedBy='" + BorrowedBy + '\'' +
                '}';
    }
    @Override
    public String toString(){
        String s=String.format("书名：《%s》 作者：%s 价格：%d 类型：%s ",name,author,price,type);
        StringBuilder sb = new StringBuilder(s);
        if(BorrowedBy==null){
            sb.append("没有被借走");
        }else{
            sb.append("被 ");
            sb.append(BorrowedBy);
            sb.append(" 借走了");
        }
        return sb.toString();
    }

    public boolean isBorrowed() {
        return BorrowedBy!=null;
    }

    public boolean equalsByName(String name) {
        //为了封装写成了这样
        //name属于String类型，需要equals判断。
        return this.name.equals(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return name.equals(book.name);
    }

    public void borrow(User user) {
        BorrowedBy= user.getUsername();
    }

    public boolean isBorrowedBy(User user) {
        if(this.BorrowedBy==null){
            return false;
        }
        return this.BorrowedBy.equals(user.getUsername());
    }

    public void returnBook(User user) {
        this.BorrowedBy=null;
    }
}
