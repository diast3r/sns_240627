package com.sns.post.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.sns.user.entity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "post")
public class PostEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	
	// @ManyToOne: 이 테이블이 N이고, 참조할 테이블이 1의 관계임. (이 테이블이 연관관계의 주인이다)
	// @JoinColumn: 의미로 보면 join + @Column이라고 보면 됨. @Column(name = "userId")인데, join을 위한 외래키인 것일 뿐. 따라서 name은 N이 가진 컬럼명으로 설정
	// referencedColumnName : 참조하는 테이블의 어떤 컬럼을 참조할 것인지. 생략 시(한 개의 컬럼만 참조할 때) PK 참조
	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "id") 
	private UserEntity user;
	
	private String content;
	
	@Column(name = "imgPath")
	private String imgPath;
	
	@Column(name = "createdAt")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name = "updatedAt")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy = "post")
	private List<CommentEntity> comments = new ArrayList<>();


}
