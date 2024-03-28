package com.firstproject.drawingcommunity.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;

    private String filename;

    private String filepath;

    private String writer;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date c_date;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false)
    private Date m_date;

    private int view;
}
