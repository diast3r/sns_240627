package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostRestController {
	
	private final PostBO postBO;
	
	
	/**
	 * 글 작성 (JPA)<br>
	 * 
	 * @param content 글 내용
	 * @param file 본문 이미지 파일
	 * @param session
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> createPost(
			@RequestParam(value = "content", required = false) String content,
			@RequestParam("file") MultipartFile file,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("code", 403);
			result.put("error_message", "로그인 후 이용해주세요.");
			return result;
		}
		String loginId = (String)session.getAttribute("userLoginId");
		
		PostEntity post = postBO.addPost(userId, loginId, content, file);
		if (post != null) {
			result.put("code", 200);
			result.put("result", "성공");
			return result;
		} else {
			result.put("code", 500);
			result.put("error_message", "글 작성에 실패했습니다. 관리자에게 문의해주세요");
			return result;
		}
		
		
		
	}
	
}
