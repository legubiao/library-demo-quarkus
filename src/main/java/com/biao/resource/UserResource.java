package com.biao.resource;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("user")
@Produces(MediaType.TEXT_PLAIN)
public class UserResource {


    /**
     * 登录验证
     *
     * @param securityContext
     * @return 如果成功，返回对应的权限等级
     */
    @GET
    @RolesAllowed({"user", "admin"})
    @Path("/login")
    public String me(@Context SecurityContext securityContext) {
        return securityContext.getUserPrincipal().getName();
    }

}
