package com.sns.like.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Like {
	private int postId;
	private int userId;
	private LocalDateTime createdAt;
}
