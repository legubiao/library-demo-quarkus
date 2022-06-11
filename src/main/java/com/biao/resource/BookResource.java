package com.biao.resource;

import com.biao.entity.Book;
import com.biao.service.BookService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("book")
@RolesAllowed({"user"})
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    BookService service;

    @ConfigProperty(name = "book.page.size", defaultValue = "25")
    Integer pageSize;                       // 进行图书搜索时，默认的单次最大返回数量

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
     * 查询服务当前使用的默认单次最大返回数量
     *
     * @return 查询结果
     */
    @GET
    @Path("pageSize")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer getPageSize() {
        return pageSize;
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
    @RolesAllowed({"admin"})
    public String create(Book book) {
        if (book.getId() == null) return "ID无效";
        if (Book.findById(book.getId()) != null) return "条码号重复";
        book.persist();
        return "OK";
    }

    /**
     * 通过标题模糊查询书籍信息，显示结果的页数
     *
     * @param title 模糊书名
     * @param mode  查询模式
     * @return 结果页数
     */
    @GET
    @Path("title/page")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer getPageByTitle(@QueryParam("title") String title,
                                  @QueryParam("mode") String mode) {
        if (title == null) return null;
        PanacheQuery<Book> query = service.getByTitle(title, mode);
        query.page(Page.ofSize(pageSize));
        return query.pageCount();
    }

    /**
     * 通过标题模糊查询书籍信息
     *
     * @param title 模糊书名
     * @param mode  查询模式
     * @return 返回书籍实体list
     */
    @GET
    @Path("title")
    public List<Book> getByTitle(@QueryParam("title") String title,
                                 @QueryParam("mode") String mode,
                                 @QueryParam("page") Integer page) {
        if (title == null) return null;
        if (page < 1) return null;
        PanacheQuery<Book> query = service.getByTitle(title, mode);
        return query.page(Page.of(page - 1, pageSize)).list();
    }

    /**
     * 根据作者搜索图书，显示结果的页数
     *
     * @param author 作者
     * @return 结果页数
     */
    @GET
    @Path("author/page")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer getPageByAuthor(@QueryParam("author") String author) {
        if (author == null) return null;
        PanacheQuery<Book> query = service.getByAuthor(author);
        query.page(Page.ofSize(pageSize));
        return query.pageCount();
    }

    /**
     * 根据作者查询图书
     *
     * @param author 作者
     * @param page   页数
     * @return 查询结果
     */
    @GET
    @Path("author")
    public List<Book> getByAuthor(@QueryParam("author") String author,
                                  @QueryParam("page") Integer page) {
        if (author == null) return null;
        if (page < 1) return null;
        PanacheQuery<Book> query = service.getByAuthor(author);
        return query.page(Page.of(page - 1, pageSize)).list();
    }
}
