package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.like.bo.LikeBO;
import com.sns.like.domain.Like;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.timeline.domain.CardDTO;
import com.sns.user.bo.UserBO;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class TimelineBO {
	
	private final PostBO postBO;
	private final CommentBO commentBO;
	private final UserBO userBO;
	private final LikeBO likeBO;
	
	// input: X
	// output: List<CardDTO>
	public List<CardDTO> generateCardList(int userId) {
		List<CardDTO> cardList = new ArrayList<>();
		
		// 글 목록
		List<PostEntity> postList = postBO.getRecentPostList(); 
		
		
		
		// 글 1개 => CardDTO로 변환 (반복문)
		CardDTO card;
		for (PostEntity postEntity : postList) {
			card = new CardDTO();
			
			card.setUser(userBO.getUserSimpleById(postEntity.getUserId()));
			card.setPost(postEntity);
			
			// 댓글 목록
			card.setComments(commentBO.generateCommentListByPostId(postEntity.getId()));
			
			// 좋아요 개수
			card.setLikes(likeBO.getLikeListByPostId(postEntity.getId(), userId).size());
			
			// 로그인한 유저가 누른 좋아요
			card.setFilledLike(likeBO.getLikeByPostIdAndUserId(postEntity.getId(), userId) != null ? true : false);
			
			
			
			// DTO 넣기 
			cardList.add(card);
		}
		
		
		
		return cardList;
	}
	
}
