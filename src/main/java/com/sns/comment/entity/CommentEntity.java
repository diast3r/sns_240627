package com.sns.comment.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 이 Entity는 안 쓰일 예정임.
 * 
 * @deprecated Entity의 join을 연습하기 위해 만들어진 객체.
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "comment")
public class CommentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "postId", referencedColumnName = "id")
//	private PostEntity post;
	@Column(name = "postId")
	private int postId; 
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "userId", referencedColumnName = "id")
//	private UserEntity user;
	
	@Column(name = "userId")
	private int userId;
	
	private String content;
	
	@Column(name = "createdAt")
	private LocalDateTime createdAt;
	
	@Column(name = "updatedAt")
	private LocalDateTime updatedAt;
}
