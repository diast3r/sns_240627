package com.sns.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sns.common.FileManagerService;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/images/**") // path  http://localhost/images/asdf_1730889214464/test.png 로 들어온 요청을 잡아줌.
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH); // file:// => 맥 또는 리눅스 file:/// => 윈도우
	}
}
