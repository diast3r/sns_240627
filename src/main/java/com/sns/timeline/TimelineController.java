package com.sns.timeline;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TimelineController {
	
	@GetMapping("/timeline")
	public String timeline() {
		return "timeline/timeline";
	}
}
