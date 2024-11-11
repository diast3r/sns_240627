package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.Comment;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentRestController {
	
	private final CommentBO commentBO;
	
	
	/**
	 * 댓글 작성<br>
	 * 
	 * @param comment 댓글 내용
	 * @param postId 댓글이 달린 post의 id
	 * @param request HttpServletRequest
	 * @param session HttpSession
	 * @return
	 */
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
	
	
	/**
	 * 댓글 삭제(Mybatis)<br>
	 * 
	 * @param commentId 선택한 댓글의 id
	 * @param session 로그인한
	 * @return
	 */
	@DeleteMapping("/delete")
	public Map<String, Object> deleteComment(
			@RequestParam("commentId") int commentId,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		
		// 로그인 여부 확인
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("code", 401);
			result.put("error_message", "로그인하세요");
			return result;
		}
		
		
		Comment comment = commentBO.getCommentById(commentId);
		if (comment.getUserId() != userId) {
			result.put("code", 403);
			result.put("error_message", "자신의 댓글만 삭제할 수 있습니다.");
			return result;
		}
		
		// delete
		int rowCount = commentBO.deleteComment(commentId);
		
		// 성공 result
		if (rowCount > 0) {
			result.put("code", 200);
			result.put("result", "삭제되었습니다.");
			return result;
		} else {
			result.put("503", "존재하지 않는 댓글입니다.");
			return result;
		}
	}
	
}
