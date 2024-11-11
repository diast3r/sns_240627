package com.sns.like.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Like {
	private int postId;
	private int userId;
	private LocalDateTime createdAt;
}
