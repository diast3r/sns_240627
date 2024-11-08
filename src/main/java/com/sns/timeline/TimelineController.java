package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class TimelineController {
	
	private final PostBO postBO;
	
	@GetMapping("/timeline")
	public String timeline(Model model) {
		List<PostEntity> postList = postBO.getPostListOrderById();
		//List<PostDto> postList = postBO.getPostListWithUserLoginId();
		
		model.addAttribute("postList", postList);
		return "timeline/timeline";
	}
	
	@GetMapping("/timeline/test")
	public String timelineTest(Model model) {
		
		List<PostEntity> postList = postBO.getPostListOrderById();
		
		model.addAttribute("postList", postList);
		return "timeline/timelineTest";
	}
}
