package com.biao.resource;

import com.biao.entity.LibraryLog;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("log")
public class LogResource {

    @ConfigProperty(name = "log.page.size", defaultValue = "25")
    Integer logPageSize;


    /**
     * 获取指定页数的日志
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param username      目标设备
     * @return 查询结果
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LibraryLog> getFirstPage(@QueryParam("startTime") String startTime,
                                         @QueryParam("endTime") String endTime,
                                         @QueryParam("username") String username,
                                         @QueryParam("page") Integer page) {
        if (page < 1) return null;
        PanacheQuery<LibraryLog> logs = getQuery(startTime, endTime, username);
        return logs.page(Page.of(page - 1, logPageSize)).list();
    }


    /**
     * 获取日志查询的页数
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param username      目标设备
     * @return 查询结果
     */
    @GET
    @Path("page")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer getPage(@QueryParam("startTime") String startTime,
                           @QueryParam("endTime") String endTime,
                           @QueryParam("username") String username) {
        PanacheQuery<LibraryLog> agvLogs = getQuery(startTime, endTime, username);
        agvLogs.page(Page.ofSize(logPageSize));
        return agvLogs.pageCount();
    }

    /**
     * 获取对日志的查询
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param username  目标设备
     * @return 查询实体
     */
    PanacheQuery<LibraryLog> getQuery(String startTime,
                                      String endTime,
                                      String username) {
        if (username == null) {
            if (startTime == null || endTime == null) {
                return LibraryLog.findAll(Sort.descending("date"));
            } else {
                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime start = LocalDateTime.parse(startTime, df);
                LocalDateTime end = LocalDateTime.parse(endTime, df);
                return LibraryLog.find("date > ?1 and date < ?2 order by date desc", start, end);
            }
        } else {
            if (startTime == null || endTime == null) {
                return LibraryLog.find("username", Sort.descending("date"), username);
            } else {
                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime start = LocalDateTime.parse(startTime, df);
                LocalDateTime end = LocalDateTime.parse(endTime, df);
                return LibraryLog.find("date > ?1 and date < ?2 and username = ?3 order by date desc", start, end, username);
            }
        }
    }
}
