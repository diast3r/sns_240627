package com.sns.post;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("postId") int postId,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		// 로그인 여부 확인
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("code", 401);
			result.put("error_message", "로그인하세요");
			return result;
		}
		
		// db 삭제
		try {
			postBO.deletePostByPostId(postId, userId);
		} catch (NoSuchElementException e) { // 게시글이 존재 안 함
			result.put("code", 403);
			result.put("error_message", "존재하지 않는 게시글입니다.");
			return result;
		} catch (Exception e) { // 그 외의 예외
			log.info("[post 삭제 실패] postId:{}, errorName: {}", postId, e.getClass().getSimpleName());
			result.put("code", 500);
			result.put("error_message", "게시글을 삭제하지 못했습니다. 관리자에게 문의해주세요.");
			return result;
		}
		
		// 응답
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
}
