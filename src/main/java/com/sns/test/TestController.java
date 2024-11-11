package com.sns.test;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.domain.CardDTO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class TestController {
	
	private final TimelineBO timelineBO;
	
	public String timelineTest(HttpSession session, Model model) {
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			userId = 0;
		}
		List<CardDTO> cardList = timelineBO.generateCardList(userId);
		
		model.addAttribute("cardList", cardList);
		
		return "timeline/timelineTest";
	}
}
