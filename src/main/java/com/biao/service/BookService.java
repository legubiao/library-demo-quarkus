package com.biao.service;

import com.biao.entity.Book;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookService {

    public PanacheQuery<Book> getByTitle(String title, String mode) {
        return switch (mode) {
            case "prefixMatch" -> Book.find("title like '" + title + "%'");
            case "fuzzySearch" -> Book.find("title like '%" + title + "%'");
            default -> Book.find("title = ?1", title);
        };
    }

    public PanacheQuery<Book> getByAuthor(String author) {
        return Book.find("author", author);
    }
}
