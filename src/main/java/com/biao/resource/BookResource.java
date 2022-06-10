package com.biao.resource;

import com.biao.entity.Book;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("book")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    /**
     * 根据条码号搜索书籍
     *
     * @param id 图书ID
     * @return 查询结果
     */
    @GET
    @Path("id/{id}")
    public Book getByID(@PathParam("id") String id) {
        return Book.findById(id);
    }

    /**
     * 创建图书信息
     *
     * @param book 图书数据
     * @return 操作结果
     */
    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public String create(Book book) {
        if (book.getId() == null) return "ID无效";
        if (Book.findById(book.getId()) != null) return "条码号重复";
        book.persist();
        return "OK";
    }
}
