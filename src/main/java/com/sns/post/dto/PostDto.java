package com.sns.post.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDto {
	private int id;
	private int userId;
	private String content;
	private String userLoginId;
	private String imgPath;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
}
