package com.sns.user.entity;

import java.time.LocalDateTime;

public interface PostWithUserLoninIdWrapper {
	public int getId();
	public String getLoginId();
	public int getUserId();
	public String getContent();
	public String getImgPath();
	public LocalDateTime getCreatedAt();
	public LocalDateTime getUpdatedAt();
}
