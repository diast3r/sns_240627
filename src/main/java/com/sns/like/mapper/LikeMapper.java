package com.sns.like.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sns.like.domain.Like;

@Mapper
public interface LikeMapper {
	
	/**
	 * 게시글의 좋아요 개수 조회(MyBatis)<br>
	 * 
	 * @param postId 좋아요 개수 조회할 post의 Id(PK)
	 * @return
	 */
	public List<Like> selectLikeListByPostId(int postId);
	
	/*
	public Like selectLikeCountByPostIdOrUserId(
			@Param("postId") int postId,
			@Param("userId") Integer userId);
	 */
	
			
	
	/**
	 * 현재 로그인한 사람이 해당 게시글에 좋아요를 눌렀는지 표시.
	 * 
	 * @param postId
	 * @param userId
	 * @return 좋아요 했으면 Like, 안 했으면 null
	 */
	public Like selectLikeByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") Integer userId);
	
	/**
	 * 좋아요 누르기(MyBatis)
	 * 
	 * @param postId
	 * @param userId
	 */
	public void insertLike(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	
	/**
	 * 좋아요 해제(MyBatis)
	 * 
	 * @param postId
	 * @param userId
	 */
	public void deleteLikeByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
}
