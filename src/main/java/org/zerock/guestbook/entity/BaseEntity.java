package org.zerock.guestbook.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass
@Getter
@EntityListeners(value= {AuditingEntityListener.class})
abstract class BaseEntity {
	@CreatedDate
	@Column(name="regdate", updatable=false)
	private LocalDateTime regDate;
	
	@LastModifiedDate
	@Column(name="moddate")
	private LocalDateTime modDate;
}
