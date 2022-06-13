package com.biao.service;

import com.biao.entity.Book;
import com.biao.entity.BookCopy;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

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

    /**
     * 图书的批量创建
     *
     * @param bookList 图书列表
     * @return 插入成功数量
     */
    public Integer bookBatchUpdate(List<Book> bookList) {
        int count = 0;
        for (Book book : bookList) {
            if (book.getId() == null) continue;
            if (Book.findById(book.getId()) != null) Book.deleteById(book.getId());
            book.persist();
            count++;
        }
        return count;
    }

    /**
     * 批量创建图书副本 如果图书在数据库里还没有，就不创建
     *
     * @param bookList 图书列表
     * @return 成功数量
     */
    public Integer bookCopyBatchCreate(List<Book> bookList) {
        int count = 0;
        for (Book book : bookList) {
            if (book.getId() == null) continue;
            if (Book.findById(book.getId()) == null) continue;
            BookCopy copy = new BookCopy();
            copy.setBookID(book.getId());
            copy.persist();
            count++;
        }
        return count;
    }
}
