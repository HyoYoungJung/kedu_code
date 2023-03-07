package com.keduit.bpro52.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
@Table(name="board")
public class Board extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY : mysql,maria에서의 번호 자동생성
	private Long bno;
	
	@Column(length = 500, nullable=false) //컬럼의 길이와 null 허용여부
	private String title; //제목
	
	@Column(length = 2000, nullable=false)
	private String content; //내용
	
//	@Column(length = 50, nullable = false)
	@ManyToOne(fetch = FetchType.LAZY) 	//Board가 N, Writer가 1(1:N) //lazy 로딩으로 지정
	private Member writer; //작성자
	
	public void changes(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
