package com.keduit.bpro01.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="tbl_memo")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Memo {
	@Id //pk 생성되도록 @Id
	@GeneratedValue(strategy = GenerationType.AUTO) //seq 생성되도록 @GeneratedValue(오라클일땐 AUTO로 주고, mysql이나 mariadb처럼 increment기능 있는 것은 IDENTITY라고 적어줘야함)
	private Long mno;
	
	@Column(length=200, nullable=false)
	private String memoText;

	@Column(nullable=false, columnDefinition="date default sysdate", insertable=false, updatable=false) //insertable=false 는 한번 등록했으면 수정하지 못하게 함
	@Temporal(TemporalType.TIMESTAMP)
	private Date regDate = new Date();
	
}
