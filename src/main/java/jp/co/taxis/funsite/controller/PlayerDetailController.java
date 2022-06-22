package jp.co.taxis.funsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.taxis.funsite.entity.PlayerEntity;
import jp.co.taxis.funsite.entity.TopicEntity;
import jp.co.taxis.funsite.service.PlayerService;
import jp.co.taxis.funsite.service.TopicService;

@Controller
@RequestMapping("player")
public class PlayerDetailController {

	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private TopicService topicService;

	/**
	 * 選手詳細画面表示メソッド
	 * 
	 * @param playerForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping("detail")
	public String detail(@RequestParam("id") int id,Model model) {

		// 選手情報・トピック情報を取得する
		PlayerEntity player = playerService.selectById(id);
		String seiza = playerService.getPlayerSeiza(id);
		List<TopicEntity> topicList = topicService.getPlayerTopic(id);

		// 出力
		model.addAttribute("player", player);
		model.addAttribute("seiza", seiza);
		model.addAttribute("topicList", topicList);
		
		//Viewの選択
		return "detail";
	}

}
