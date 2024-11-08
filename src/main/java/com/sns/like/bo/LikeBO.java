package com.sns.like.bo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sns.like.domain.Like;
import com.sns.like.mapper.LikeMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LikeBO {
	
	private final LikeMapper likeMapper;
	
	public List<Like> getLikeListByPostId(int postId) {
		return likeMapper.selectLikeListByPostId(postId);
	}
}
