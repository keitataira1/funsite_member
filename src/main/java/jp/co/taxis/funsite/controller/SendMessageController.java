package jp.co.taxis.funsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.taxis.funsite.service.MemberService;
import jp.co.taxis.funsite.service.SupportMessageService;
import jp.co.taxis.funsite.service.TopicService;

@Controller
@RequestMapping("message")
public class SendMessageController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private SupportMessageService supportMessageService;
	
	@Autowired
	private TopicService topicService;

	@GetMapping("send")
	public String send(@RequestParam("id") int id, Model model) {
		
		return "message/send";
	}
	
	@PostMapping("register")
	public String register() {
		return "redirect:send";
	}
	
}
