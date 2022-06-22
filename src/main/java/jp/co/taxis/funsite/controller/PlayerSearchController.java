package jp.co.taxis.funsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.taxis.funsite.entity.PlayerEntity;
import jp.co.taxis.funsite.service.PlayerService;

@Controller
@RequestMapping("player")
public class PlayerSearchController {

	/** playerサービス */
	@Autowired
	private PlayerService playerService;

	/** 検索→表示の画面表示 */
	@RequestMapping("search")
	public String searchDisplay(Model model) {

		List<PlayerEntity> playerList = playerService.selectAll();
		model.addAttribute("playerList", playerList);
		return "search";
	}
	
	@RequestMapping("test")
	public String seatchTest(@RequestParam("name") String name,Model model) {
		List<PlayerEntity> playerList = playerService.selectLikeName(name);
		model.addAttribute("playerList",playerList);
		model.addAttribute("name", name);
		return "search";
	}

}
