package jp.co.taxis.funsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@RequestMapping(value = "search", method = { RequestMethod.GET ,RequestMethod.POST})
	public String searchDisplay(@ModelAttribute("search") Model model, @RequestParam(name = "name") PlayerEntity player) {

		List<PlayerEntity> playerList = playerService.selectLikeName(player.getName());
		model.addAttribute("playerList", playerList);

		return "player/search";
	}

}
