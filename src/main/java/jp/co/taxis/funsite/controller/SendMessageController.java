package jp.co.taxis.funsite.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.taxis.funsite.dto.UserDto;
import jp.co.taxis.funsite.entity.SupportMessageEntity;
import jp.co.taxis.funsite.entity.TopicEntity;
import jp.co.taxis.funsite.form.MessageForm;
import jp.co.taxis.funsite.service.MemberService;
import jp.co.taxis.funsite.service.SupportMessageService;
import jp.co.taxis.funsite.service.TopicService;

@Controller
@RequestMapping("message")
public class SendMessageController {

	@Autowired
	private SupportMessageService supportMessageService;

	@Autowired
	private TopicService topicService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private UserDto userDto;

	@RequestMapping("send")
	public String send(@RequestParam("id") int id, @ModelAttribute("text") MessageForm text, Model model) {

		if (userDto.getMemberEntity() == null) {
			return "redirect:../user/login";
		}
		
		AddAttribute(text, id, model);

		return "send";
	}

	@RequestMapping("register")
	public String register(@ModelAttribute("text") @Validated MessageForm text, BindingResult result,
			RedirectAttributes redirectAttributes,Model model) {
		
		if(result.hasErrors()) {
			AddAttribute(text,text.getTopicId(), model);
			return "send";
		}

		SupportMessageEntity message = new SupportMessageEntity();
		message.setMember(memberService.selectById(userDto.getMemberEntity().getId()));
		message.setMessage(text.getMessage());
		message.setTopic(topicService.getTopic(text.getTopicId()));
		message.setSendDatetime(LocalDate.now());
		supportMessageService.insert(message);
		
		redirectAttributes.addAttribute("id", text.getTopicId());

		return "redirect:send";
	}
	
	private void AddAttribute(@ModelAttribute("text") MessageForm text, int id,Model model) {
		
		text.setTopicId(id);
		
		TopicEntity topic = topicService.getTopic(id);
		model.addAttribute("topic", topic);
		
		List<SupportMessageEntity> messageList = supportMessageService.MessageByTopicId(id);
		model.addAttribute("messageList",messageList);
	}

}
