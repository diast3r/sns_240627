package com.sns.like.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sns.like.domain.Like;

@Mapper
public interface LikeMapper {
	public List<Like> selectLikeListByPostId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public Like selectLikeByPostIdAndUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public void insertLike(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public void deleteLike(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
}
