package com.sns.user.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSimpleDTO {
	private String loginId;
	private String imgPath;
	private String name;
}
