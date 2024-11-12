package com.sns.comment.domain;

import com.sns.user.domain.UserSimpleDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 댓글 1개
public class CommentDTO {
	// 댓글 1개
	private Comment comment;
	
	private UserSimpleDTO user;
	
}
