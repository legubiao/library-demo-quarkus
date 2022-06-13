package com.biao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LibraryLog extends PanacheEntity {

    String type;            // 日志类型
    String username;        // 操作的用户
    String description;     // 日志描述

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    LocalDateTime date;     // 日志日期


    public static void add(String type, String username, String description) {
        LibraryLog log = new LibraryLog();
        log.type = type;
        log.username = username;
        log.description = description;
        log.date = LocalDateTime.now();
        log.persist();
    }
}
