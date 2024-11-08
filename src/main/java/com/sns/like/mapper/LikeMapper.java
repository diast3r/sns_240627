package com.sns.like.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sns.like.domain.Like;

@Mapper
public interface LikeMapper {
	public List<Like> selectLikeListByPostId(int postId);
}
