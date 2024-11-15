package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentDTO;
import com.sns.comment.mapper.CommentMapper;
import com.sns.user.bo.UserBO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
	
	/**
	 * Comment + UserEntity(작성자)로 CommentDTO 만들기 
	 * @param postId
	 * @return
	 */
	// input: 글 번호
	// output: List<CommentDTO>
	public List<CommentDTO> generateCommentListByPostId(int postId) {
		List<CommentDTO> commentDTOList = new ArrayList<>();
		
		
		List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);
		
		for (Comment comment : commentList) {
			CommentDTO commentDTO = new CommentDTO();
			
			// 댓글 1개
			commentDTO.setComment(comment);
			
			// 댓글쓴이
			// UserSimpleDTO로 바꾸기
			commentDTO.setUser(userBO.getUserSimpleById(comment.getUserId()));
			
			commentDTOList.add(commentDTO);
		}
		
		return commentDTOList;
	};
	
	/**
	 * id로 조회해서 Comment 하나 가져오기(Mybatis)
	 * 
	 * @param id Comment의 id
	 * @return Comment
	 */
	public Comment getCommentById(int id) {
		return commentMapper.selectCommentById(id);
	}
	
	
	/**
	 * 댓글 삭제 (Mybatis)
	 * @param id 삭제할 comment의 id
	 * @return
	 */
	public int deleteCommentById(int id) {
		return commentMapper.deleteComment(id);
	}

	public int deleteCommentListByPostId(int postId) {
		int rowCount = commentMapper.deleteCommentListByPostId(postId);
		log.info("[comment 삭제] postId:{}", postId);
		
		return rowCount;
	}
}
