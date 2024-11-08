package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;
import com.sns.user.entity.UserEntity;
import com.sns.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostBO {
	
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final FileManagerService fileManager;  
	
	
	public List<PostEntity> getPostListOrderById() {
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
		
		UserEntity user = userRepository.findById(userId).orElseThrow();
		
		return postRepository.save(PostEntity
				.builder()
				.user(user)
				.content(content)
				.imgPath(filePath)
				.build());
		
	}
	
}
