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
	
	public void likeToggle(int postId, int userId) {
		Like like = getLikeByPostIdAndUserId(postId, userId);
		if (like == null) {
			likeMapper.insertLike(postId, userId);
		} else {
			likeMapper.deleteLike(postId, userId);
		}
	}
	
	public List<Like> getLikeListByPostId(int postId, int userId) {
		return likeMapper.selectLikeListByPostId(postId, userId);
	}
	
	public Like getLikeByPostIdAndUserId(int postId, int userId) {
		return likeMapper.selectLikeByPostIdAndUserId(postId, userId);
	}
	
	
}
