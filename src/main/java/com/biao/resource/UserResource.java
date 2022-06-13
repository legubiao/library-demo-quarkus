package com.biao.resource;

import com.biao.entity.User;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.Objects;

@Path("user")
@Produces(MediaType.TEXT_PLAIN)
public class UserResource {


    /**
     * 登录验证
     *
     * @param securityContext 登录信息
     * @return 如果成功，返回对应的权限等级
     */
    @GET
    @RolesAllowed({"user", "admin"})
    @Path("/login")
    public String me(@Context SecurityContext securityContext) {
        User user = User.find("username", securityContext.getUserPrincipal().getName()).firstResult();
        return user.role;
    }

    /**
     * 创建新用户
     *
     * @param username 用户名
     * @param password 密码
     * @param role     权限等级
     * @return 操作结果
     */
    @GET
    @Path("add")
    @RolesAllowed("admin")
    @Transactional
    public String createUser(@QueryParam("username") String username,
                             @QueryParam("password") String password,
                             @QueryParam("role") String role) {
        if (username == null || password == null || role == null) return "信息无效";
        if (User.count("username", username) != 0) return "用户名已使用";
        if (!Objects.equals(role, "admin") && !Objects.equals(role, "user")) return "无效的权限类型";
        User.add(username, password, role);
        return "OK";
    }

    /**
     * 删除自己
     *
     * @param securityContext 登录信息
     * @return 操作结果
     */
    @DELETE
    @RolesAllowed({"user", "admin"})
    public String deleteMe(@Context SecurityContext securityContext) {
        User.delete("username", securityContext.getUserPrincipal().getName());
        return "OK";
    }

    /**
     * 根据ID删除用户
     *
     * @param id 用户ID
     * @return 操作结果
     */
    @DELETE
    @RolesAllowed("admin")
    @Path("{id}")
    public String deleteByID(@PathParam("id") Long id) {
        User.deleteById(id);
        return "OK";
    }
}
