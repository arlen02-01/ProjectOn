package com.arlen.ProjectOn.domain.common;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

 @CreatedDate
 @Column(updatable = false)
 private LocalDateTime createdAt;   // 최초 생성 시간

 @LastModifiedDate
 private LocalDateTime updatedAt;   // 마지막 수정 시간
}
