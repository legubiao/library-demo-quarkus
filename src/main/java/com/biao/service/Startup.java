package com.biao.service;

import com.biao.entity.User;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.transaction.Transactional;


@Singleton
public class Startup {

    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {
        User.deleteAll();
        User.add("admin", "admin", "admin");
        User.add("user", "user", "user");
    }
}
