package com.biao.resource;

import com.biao.entity.Book;
import com.biao.entity.BookCopy;
import com.biao.entity.LibraryLog;
import com.biao.service.BookService;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.time.LocalDateTime;
import java.util.List;

@Path("copy")
@RolesAllowed({"user", "admin"})
public class BookCopyResource {

    @Inject
    BookService service;

    @ConfigProperty(name = "book.borrow.duration", defaultValue = "3")
    Integer borrowDuration;                       // 借书时的预期归还周期, 单位为月

    /**
     * 查询指定用户的借阅情况
     *
     * @param securityContext 用户信息
     * @return 查询结果
     */
    @GET
    @Path("borrowed")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BookCopy> getBorrowed(@Context SecurityContext securityContext) {
        return BookCopy.list("borrower", securityContext.getUserPrincipal().getName());
    }

    /**
     * 根据书籍ID查询所有副本
     *
     * @param bookID 书籍ID
     * @return 查询结果
     */
    @GET
    @Path("bookID/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BookCopy> getByBookID(@PathParam("id") String bookID) {
        return BookCopy.list("bookID", bookID);
    }

    /**
     * 借阅图书
     *
     * @param securityContext 用户信息
     * @param copyID          目标图书信息
     * @return 操作而结果
     */
    @GET
    @Path("borrow/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String borrowBook(@Context SecurityContext securityContext,
                             @PathParam("id") Long copyID) {
        BookCopy copy = BookCopy.findById(copyID);
        if (copy == null) return "图书信息无效";
        if (copy.getBorrower() != null) return "这本书当前已经借出";
        copy.setBorrower(securityContext.getUserPrincipal().getName());
        copy.setBorrowDate(LocalDateTime.now());
        copy.setDueDate(LocalDateTime.now().plusMonths(borrowDuration));
        return "OK";
    }

    /**
     * 批量创建图书副本
     *
     * @param bookList 图书信息表
     * @return 操作结果
     */
    @POST
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    @Path("list")
    public Integer batchCreateByBook(@Context SecurityContext securityContext,
                                     List<Book> bookList) {
        int result = service.bookCopyBatchCreate(bookList);
        LibraryLog.add("副本插入",securityContext.getUserPrincipal().getName(),"成功插入" + result + "条图书副本");
        return result;
    }

}
