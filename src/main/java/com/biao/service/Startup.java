package com.biao.service;

import com.biao.entity.User;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.transaction.Transactional;


@Singleton
public class Startup {

    @ConfigProperty(name = "admin.username", defaultValue = "admin")
    String adminName;                        // 默认管理员用户的账户名
    @ConfigProperty(name = "admin.password", defaultValue = "admin")
    String adminPwd;                         // 默认管理员用户的密码


    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {
        User.deleteAll();
        User.add(adminName, adminPwd, "admin");
    }
}
