package jp.co.taxis.funsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.taxis.funsite.form.PlayerForm;
import jp.co.taxis.funsite.service.PlayerService;
import jp.co.taxis.funsite.service.TopicService;

@Controller
@RequestMapping("player")
public class PlayerDetailController {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private TopicService topicService;

	@RequestMapping(value = "player", method = { RequestMethod.POST })
	public String detail(@ModelAttribute("detail") @Validated PlayerForm playerForm, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			return "error";
		}
		
		//PlayerEntity player=playerService.selectById();
		
		return "result";
	}
	
}
