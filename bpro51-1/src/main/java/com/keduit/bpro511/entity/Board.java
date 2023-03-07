package com.keduit.bpro511.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="tbl_board1")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Board extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY : mysql,maria에서의 번호 자동생성
	private Long bno;
	
	@Column(length = 200, nullable=false)
	private String title; //제목
	
	@Column(length = 2000, nullable=false)
	private String content; //내용
	
	@Column(length = 50, nullable = false)
	private String writer; //작성자
}
