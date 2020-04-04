package com.myframework.sbase.sys.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date createdDate = Calendar.getInstance().getTime();

    @Column(nullable = false)
    private boolean isDeleted = false;

    public BaseModel() {
    }

    public BaseModel(Long id, Date createdDate, boolean isDeleted) {
        this.id = id;
        this.createdDate = createdDate;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
