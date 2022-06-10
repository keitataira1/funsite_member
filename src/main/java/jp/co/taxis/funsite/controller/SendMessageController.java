package jp.co.taxis.funsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.taxis.funsite.entity.TopicEntity;
import jp.co.taxis.funsite.repository.MemberRepository;
import jp.co.taxis.funsite.repository.SupportMessageRepository;
import jp.co.taxis.funsite.repository.TopicRepository;

@Controller
@RequestMapping("message")
public class SendMessageController {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private SupportMessageRepository supportMesseageRepository;
	
	@Autowired
	private TopicRepository topicRepository;

	@GetMapping("send")
	public String send(@RequestParam("id") int id, Model model) {
		
		TopicEntity topic = new TopicEntity();
		topic = topicRepository.findById(id).orElse(null);
		model.addAttribute(topic);
		
		
		
		return "message/send";
	}
	
	@PostMapping("legister")
	public String legister() {
		return "redirect:send";
	}
	
}
