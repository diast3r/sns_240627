package com.sns.comment.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Comment {
	private int id;
	private int postId;
	private int userId;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@Builder
	private Comment(String content, int postId, int userId) {
		this.content = content;
		this.postId = postId;
		this.userId = userId;
	}
	
	// .builder()
	// .content() , .postId(), .userId() 가능
	// .id(), .createdAt(), .updatedAt() 불가능
}
