package com.biao.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * 图书信息基本类
 * 表示同一索书号的图书的基本信息
 */

@Entity
@Getter
@Setter
public class Book extends PanacheEntityBase {

    @Id
    String id;              // 图书的条码号
    String title;           // 图书标题
    String author;          // 作者
    String publisher;       // 出版社
    String isbn;            // ISBN编码
    String callNumber;      // 索书号

    public static void add(String id, String title, String author, String publisher, String isbn, String callNumber) {
        Book book = new Book();
        book.id = id;
        book.title = title;
        book.author = author;
        book.publisher = publisher;
        book.isbn = isbn;
        book.callNumber = callNumber;
        book.persist();
    }
}
