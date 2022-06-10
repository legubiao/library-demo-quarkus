package com.biao.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * 图书的副本信息
 * 对应现实中的一本图书
 */
@Entity
@Getter
@Setter
public class BookCopy extends PanacheEntity {

    String shelfID;                 // 所在书架的位置

    String borrower;                // 借阅人
    LocalDateTime borrowDate;       // 借出时间
    LocalDateTime dueDate;          // 预计归还时间

}
