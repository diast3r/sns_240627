package com.sns.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sns.post.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer>{
	public List<PostEntity> findByOrderByIdDesc();
	
	
//	@Query(value = "select * from post p join user u on p.userId = u.id order by p.id desc", 
//			countQuery = "select * from post p join user u on p.userId = u.id order by p.id desc", 
//			nativeQuery = true)
//	public List<PostEntity> selectPostListWithUserLoginId();
}
