package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentDTO;
import com.sns.comment.mapper.CommentMapper;
import com.sns.user.bo.UserBO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentBO {
	
	private final CommentMapper commentMapper;
	private final UserBO userBO;
	
	/**
	 * 댓글 작성(추가)-MyBatis
	 * 
	 * @param postId 댓글이 달릴 post의 id
	 * @param userId 댓글 작성자 user의 id
	 * @param content 댓글 내용
	 * @return
	 */
	public int addComment(int postId, int userId, String content) {
		return commentMapper.insertComment(postId, userId, content);
	}
	
	// input: 글 번호
	// output: List<CommentDTO>
	public List<CommentDTO> generateCommentListByPostId(int postId) {
		List<CommentDTO> commentDTOList = new ArrayList<>();
		List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);
		
		CommentDTO commentDTO;
		for (Comment comment : commentList) {
			commentDTO = new CommentDTO();
			commentDTO.setComment(comment);
			commentDTO.setUser(userBO.getUserEntityById(comment.getUserId()));
			
			commentDTOList.add(commentDTO);
		}
		
		return commentDTOList;
	};
	
}
