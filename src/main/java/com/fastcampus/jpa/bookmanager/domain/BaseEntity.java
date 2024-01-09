package com.fastcampus.jpa.bookmanager.domain;


import com.fastcampus.jpa.bookmanager.domain.listener.Auditable;
import java.time.LocalDateTime;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@MappedSuperclass // 상속받는 엔티티의 필드로 추가됨
@EntityListeners(value = AuditingEntityListener.class)
public class BaseEntity implements Auditable {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;



}
