package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class LikeRestController {
	
	private final LikeBO likeBO;
	
	// GET(이전에 하던 방식) : /like?postId=3	=> @RequestParam("postId")
	// GET: /like/3        /like/{postId}	=> @PathVariable(name="postId")
	@GetMapping("/like/{postId}")
	public Map<String, Object> likeToggle(
			@PathVariable(name="postId") int postId,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		// 로그인 여부 확인
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("code", 401);
			result.put("error_message", "로그인 후 사용 가능합니다.");
			return result;
		}
		
		// toggle 요청 => BO
		likeBO.likeToggle(postId, userId);
		
		
		// 응답값
		result.put("code", 200);
		result.put("result", "좋아요 토글");
		
		return result;
		
	}
}
