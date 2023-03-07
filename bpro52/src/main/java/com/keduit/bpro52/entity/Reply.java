package com.keduit.bpro52.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
@ToString(exclude = "board")
public class Reply extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //PK가 AI로 설정이 됨
	 private Long rno; //Auto Increment임
	 private String text;
	 private String replyer;
	 
	 @ManyToOne(fetch = FetchType.LAZY) 			//board와 replyer는 1대N 관계
	 private Board board; //board가 N, replyer가 1
}
