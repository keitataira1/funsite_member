package jp.co.taxis.funsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.taxis.funsite.entity.PlayerEntity;
import jp.co.taxis.funsite.form.PlayerForm;
import jp.co.taxis.funsite.service.PlayerService;

@Controller
@RequestMapping("player")
public class PlayerSearchController {

	/** playerサービス */
	@Autowired
	private PlayerService playerService;

	/** 画面表示のみ */
	@RequestMapping(value = "search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchInput(@ModelAttribute("player") PlayerForm playerForm) {
		return "player/player_search";
	}

	/** 検索→表示の画面表示 */
	@RequestMapping(value = "search2", method = { RequestMethod.POST })
	public String searchDisplay(@ModelAttribute("player") Model model, @Validated PlayerForm playerForm,
			BindingResult result) {

		if (result.hasErrors()) {
			return "error";
		}
		List<PlayerEntity> playerList = playerService.selectLikeName("%" + playerForm.getName() + "%");
		model.addAttribute("playerList", playerList);

		return "player/player_search2";
	}

}
