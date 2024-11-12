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
	
	// 토글: 좋아요/해제
	// input: postId, userId
	// output: X
	public void toggleLike(int postId, int userId) {
		// 좋아요 눌렀는지 확인
		if (filledLikeByPostIdAndUserId(postId, userId) == null) {
			// 좋아요 안 눌렀으면 누르기
			likeMapper.insertLike(postId, userId);
		} else {
			// 좋아요 눌렀으면 없애기
			likeMapper.deleteLikeByPostIdUserId(postId, userId);
		}
	}
	
	/**
	 * 게시글에 눌린 좋아요(Like) 리스트 조회<br>
	 * 
	 * @param postId
	 * @return
	 */
	public List<Like> getLikeListByPostId(int postId) {
		return likeMapper.selectLikeListByPostId(postId);
	}
	
	public Like filledLikeByPostIdAndUserId(int postId, Integer userId) {
		Like filledLike = likeMapper.selectLikeByPostIdUserId(postId, userId);
		
		return filledLike != null ? filledLike : null;
	}
	
}
