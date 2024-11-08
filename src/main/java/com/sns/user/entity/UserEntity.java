package com.sns.user.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.sns.post.entity.PostEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "user")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "loginId")
	private String loginId;
	
	private String password;
	
	private String name;
	
	private String email;
	
	@Column(name = "imgPath")
	private String imgPath;
	
	@Column(name = "createdAt")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name = "updatedAt")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	
	/*
	 * @OneToMany 이 테이블이 1:N 관계임을 알려줌. 연관관계의 주인?: 1:N에서 관계를 정의하는 외래키를 가지고 있는 N쪽이
	 * 주인이다. db에서 join할 때는 N쪽에만 외래키를 만들면 1 <= N join이나 N <= 1 join 양방향으로 가능했지만,
	 * 객체에서는 1과 N 양쪽에 단방향 연관관계를 설정해줘야 가능하다. 한쪽만 설정하면 단방향 연관관계. 1쪽에는 필드로 @OneToMany와
	 * List<주인 Entity>를, N쪽에는 @JoinColumn과 @ManyToOne를 붙인 mappedBy는 1쪽에서 하는 설정 (주인
	 * 쪽은 다른 설정)
	 */
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<PostEntity> posts = new ArrayList<>(); // 초기화하지 않으면 NullPointException 발생함.
}
