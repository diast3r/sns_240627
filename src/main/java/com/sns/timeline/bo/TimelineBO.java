package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.Comment;
import com.sns.like.bo.LikeBO;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.timeline.domain.CardDTO;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

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
	public List<CardDTO> generateCardList() {
		List<CardDTO> cardList = new ArrayList<>();
		
		// 글 목록
		List<PostEntity> postList = postBO.getRecentPostList(); 
		
		
		
		// 글 1개 => CardDTO로 변환 (반복문)
		CardDTO card;
		for (PostEntity post : postList) {
			card = new CardDTO();
			
			card.setUser(userBO.getUserEntityById(post.getUserId()));
			card.setPost(post);
			
			// 댓글 목록
			card.setComments(commentBO.generateCommentListByPostId(post.getId()));
			card.setLikes(likeBO.getLikeListByPostId(post.getId()).size());
			
			// DTO 넣기 
			cardList.add(card);
		}
		
		
		
		return cardList;
	}
}
