package com.sns.post.bo;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostBO {
	
	private final PostRepository postRepository;
	private final FileManagerService fileManager;  
	private final LikeBO likeBO;
	private final CommentBO commentBO;
	
	public List<PostEntity> getRecentPostList() {
		return postRepository.findByOrderByIdDesc();
	}
	
	
	/**
	 * 글 작성(JPA)<br>
	 * 
	 * @param userId 글 작성자 id
	 * @param loginId 글 작성자 loginId - db에 저장할 파일 경로 생성에 사용함
	 * @param content 글 내용
	 * @param file 글 본문 이미지 파일
	 * @return
	 */
	public PostEntity addPost(int userId, String loginId, String content, MultipartFile file) {
		String filePath = fileManager.uploadFile(file, loginId);
		
		return postRepository.save(PostEntity
				.builder()
				.userId(userId)
				.content(content)
				.imgPath(filePath)
				.build());
	}

	@Transactional
	public void deletePostByPostId(int postId, int userId) throws Exception {
		// 일치하는 post 없을 시 
		PostEntity post = postRepository.findById(postId).orElseThrow();
		
		// userId로 검증
		if (post.getUserId() != userId) {
			throw new Exception();
		}
		
		likeBO.deleteLikeListByPostId(postId);
		
		commentBO.deleteCommentListByPostId(postId);
		
		postRepository.delete(post); // 엔티티를 넘기면 id(PK)로 삭제해줌
		log.info("[post 삭제] postId:{}", postId);

		// 파일 삭제는 롤백이 안 될 수도 있으니 맨 마지막에 하기.
		fileManager.deleteFile(post.getImgPath());
		log.info("[이미지 파일 삭제] postId:{} imgPath:{}", postId, post.getImgPath());
	}
	
}
