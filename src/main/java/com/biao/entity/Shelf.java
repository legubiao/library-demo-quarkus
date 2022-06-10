package com.biao.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Shelf extends PanacheEntityBase {

    @Id
    String id;              // 书架ID
    String library;         // 分馆
    String room;            // 阅览室

    @Column(name = "shelfRow")
    String row;             // 书架排
    String col;             // 书架列
    Integer level;          // 书架层
}
