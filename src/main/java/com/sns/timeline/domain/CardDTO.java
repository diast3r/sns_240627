package com.sns.timeline.domain;

import java.util.List;

import com.sns.comment.domain.CommentDTO;
import com.sns.post.entity.PostEntity;
import com.sns.user.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;

// 글 1개 => card 1개
@Getter
@Setter
public class CardDTO {
	// 글 1개
	private PostEntity post;
	
	// 글쓴이
	// TODO 민감한 정보(비밀번호 등)이 포함돼있으니까 UserSimpleDTO를 만들어서 사용하는 것 연습하기
	private UserEntity user;
	
	// 댓글 N개
	private List<CommentDTO> comments;
	
	// 좋아요 N개
	private int likes;
	
	
}
