package com.bowen.library.book;

import com.bowen.library.user.User;
import sun.security.x509.GeneralSubtree;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class BookStorage {
    private Book[] array;
    private int size;

    public BookStorage() {
        this.array = new Book[20];
        this.size =0;
    }

    private static final File file=new File("D:/BookStorage.txt");

    public static BookStorage loadFromFile() {
        BookStorage bookStorage=new BookStorage();
        if(!file.exists()){
            return bookStorage;
        }
        try {
            Scanner scanner=new Scanner(file,"UTF-8");
            while(scanner.hasNextLine()){
                String line=scanner.nextLine();
                //将得到的每一行的字符串进行分割，
                String[] split = line.split("@");
                if(split.length!=5){
                    throw new RuntimeException("数据异常");
                }
                String name=split[0];
                String author=split[1];

                String priceStr=split[2];
                int price;
                try {
                    price=Integer.parseInt(priceStr);
                }catch (NumberFormatException exc){
                    throw new RuntimeException("文件格式错误");
                }
                String type=split[3];

                String BorrowedByStr=split[4];
                String BorrowedBy;
                if(BorrowedByStr.equals("null")){
                    BorrowedBy=null;    //把字符串"null"转换成为null
                }else {
                    BorrowedBy=BorrowedByStr;
                }
                //将读取的信息载入到生成的类中
                Book book=new Book();
                book.name=name;
                book.author=author;
                book.price=price;
                book.type=type;
                book.BorrowedBy=BorrowedBy;

                //scanner.close();
                bookStorage.add(book);
            }
            scanner.close();
            return bookStorage;
        }catch (IOException exc){
            throw new RuntimeException(exc);
        }
    }

    //尾插
    public void add(Book book){
        //1.确保容量够用
        ensureCapacity();
        array[size++]=book;
        saveToFile();
    }

    private void ensureCapacity() {
        if(size<array.length){
            return;
        }
        //进行扩容
        array= Arrays.copyOf(array,array.length*2);
    }

    public Book[] toArray() {
        /*
        直接返回array的话，由于真正的书书籍只有size本，返回array其长度为array。length
        一旦把array返回的话，会发生数组对象逃逸，别人怎么操作数组，我们完全无法控制。
        */
        return Arrays.copyOf(array,size);//但是这还是属于浅拷贝
    }

    public Book searchByName(String name) {
        for (int i = 0; i < size; i++) {
            Book book=array[i];
            if(book.equalsByName(name)){
                return book;
            }
        }
        return null;
    }

    public void remove(Book book) {
        for (int i = 0; i < array.length; i++) {
            Book book1=array[i];
            if(book1.equals(book)){
                array[i]=array[size-1];
                array[size-1]=null;
                size--;
                return;
            }
        }
    saveToFile();
    }

    public void saveToFile() {
        try {
            PrintWriter writer=new PrintWriter("D:/BookStorage.txt");
            for (int i = 0; i < size; i++) {
                Book book=array[i];
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append(book.name);
                stringBuilder.append("@");
                stringBuilder.append(book.author);
                stringBuilder.append("@");
                stringBuilder.append(book.price);
                stringBuilder.append("@");
                stringBuilder.append(book.type);
                stringBuilder.append("@");
                stringBuilder.append(book.BorrowedBy);
                stringBuilder.append("@");

                //此处换行
                writer.println(stringBuilder.toString());
            }
            writer.flush();
            writer.close();

        }catch (IOException exc){
            throw new RuntimeException("写入错误！");
        }
    }

}
