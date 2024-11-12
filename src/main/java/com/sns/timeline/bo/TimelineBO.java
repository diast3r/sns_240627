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
	
	// input: 로그인된 사람 Id(비로그인도 가능 => null 될 것)
	// output: List<CardDTO>
	public List<CardDTO> generateCardList(Integer userId) {
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
			card.setLikeCount(likeBO.getLikeListByPostId(postEntity.getId()).size());
			
			// 로그인한 사용자가 좋아요 눌렀는지 여부
			// 1. 비로그인 => 빈 하트
			// 2. 로그인 => 누른 적 없음. 빈 하트
			// 3. 로그인 => 누른 적 있음. 채워진 하트
			// => 이 분기는 timelineBO가 할 게 아니라 likeBO가 할 것. 역할 구분을 확실히 하자.
			card.setFilledLike(likeBO.filledLikeByPostIdAndUserId(postEntity.getId(), userId));
			
			
			
			// DTO 넣기 
			cardList.add(card);
		}
		
		
		
		return cardList;
	}
	
}
