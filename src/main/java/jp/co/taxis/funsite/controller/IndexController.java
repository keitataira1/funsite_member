package jp.co.taxis.funsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.taxis.funsite.entity.PlayerEntity;
import jp.co.taxis.funsite.entity.TopicEntity;
import jp.co.taxis.funsite.service.PlayerService;
import jp.co.taxis.funsite.service.TopicService;

@Controller
public class IndexController {
	
	@Autowired
	TopicService topicService;
	
	@Autowired
	PlayerService playerService;

	/**
	 * 一覧画面
	 * 
	 * @param model モデル
	 * @return View
	 */
	@GetMapping("index")
	public String index(Model model) {
		
		List<TopicEntity> topicList = topicService.getLimit3Topic();
		model.addAttribute("topicList", topicList);
		
		List<PlayerEntity> playerList = playerService.selectAll();
		model.addAttribute("playerList", playerList);
		
		return "index";
	}

}
