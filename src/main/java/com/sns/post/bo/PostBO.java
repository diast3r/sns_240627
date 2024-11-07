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

@Service

public class PostBO {
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FileManagerService fileManager;  
	
	public List<PostEntity> getPostListWithUserLoginId() {
		return postRepository.findByOrderByIdDesc();
	}
	
	
	/*
	public List<PostDto> getPostListWithUserLoginId() {
		//return postRepository.findByOrderByIdDesc(); // ORDER BY id로 조건을 걸어야 인덱스를 탐.
		List<PostDto> postDtoList = new ArrayList<>();
		List<PostEntity> postEntityList = postRepository.findByOrderByIdDesc();
		PostDto postDto;
		for (PostEntity postEntity : postEntityList) {
			postDto = new PostDto();
			postDto.setId(postEntity.getId());
			postDto.setUserId(postEntity.getUser().getId());
			postDto.setContent(postEntity.getContent());
			postDto.setImgPath(postEntity.getImgPath());
			postDto.setCreatedAt(postEntity.getCreatedAt());
			postDto.setUpdatedAt(postEntity.getUpdatedAt());
			postDto.setUserLoginId(postEntity.getUser().getLoginId());
			postDtoList.add(postDto);
		}
		return postDtoList;
	} 
	*/
	
	public int addPost(int userId, String loginId, String content, MultipartFile file) {
		String filePath = fileManager.uploadFile(file, loginId);
		
		UserEntity user = userRepository.findById(userId).orElseThrow();
		
		postRepository.save(PostEntity
				.builder()
				.user(user)
				.content(content)
				.imgPath(filePath)
				.build());
		
		
		
		
		return 0;
	}
	
}
