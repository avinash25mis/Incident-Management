package com.model.common;


import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements GenericEntity {

    @Column(name = "UPDATED_DATE")
    protected LocalDateTime updatedDate;
    @Column(name = "CREATED_DATE")
    protected LocalDateTime createdDate;
    @Column(name = "CREATED_BY")
    protected String createdBy;
    @Column(name = "UPDATED_BY")
    protected String updatedBy;



    @PrePersist
    public void beforeSave(){
        this.createdDate=LocalDateTime.now();
        this.updatedDate=LocalDateTime.now();
        if(StringUtils.isEmpty(this.createdBy)){
            this.createdBy="default";
        }    if(StringUtils.isEmpty(this.updatedBy)){
            this.createdBy="default";
        }
    }

    @PreUpdate
    public void beforeUpdate() {
        this.updatedDate=LocalDateTime.now();
        if(StringUtils.isEmpty(this.updatedBy)){
            this.createdBy="default";
        }
    }


}
