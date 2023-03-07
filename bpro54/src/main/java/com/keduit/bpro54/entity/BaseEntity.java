package com.keduit.bpro54.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass
@EntityListeners(value= {AuditingEntityListener.class})
@Getter
abstract class BaseEntity { //다른곳에서 사용할수있도록 추상클래스로 생성한 것!
	
	@CreatedDate
	@Column(name="regdate", updatable=false)
	private LocalDateTime regDate; //등록일자
	
	@LastModifiedDate
	@Column(name="updatedate")
	private LocalDateTime updateDate; //수정일자
}
