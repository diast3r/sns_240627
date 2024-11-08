package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentRestController {
	
	private final CommentBO commentBO;
	
	@PostMapping("/create")
	public Map<String, Object> createComment(
			@RequestParam("comment") String comment, 
			@RequestParam("postId") int postId,
			HttpServletRequest request,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		// 로그인 안 한사람은 입구컷!!!
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("code", 403);
			result.put("error_message", "로그인한 사람만 댓글 작성이 가능합니다.");
			return result;
		}
		
		// db input
		int rowCount = commentBO.addComment(postId, userId, comment);
		if (rowCount > 0) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", "댓글 작성에 실패했습니다.");
		}
		
		
		return result;
	}
	
}
