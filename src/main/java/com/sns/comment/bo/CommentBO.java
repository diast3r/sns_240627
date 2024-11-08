package com.sns.comment.bo;

import org.springframework.stereotype.Service;

import com.sns.comment.mapper.CommentMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentBO {
	
	private final CommentMapper commentMapper;
	
	
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
	
}
