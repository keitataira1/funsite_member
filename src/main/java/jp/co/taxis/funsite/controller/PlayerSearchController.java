package jp.co.taxis.funsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.taxis.funsite.entity.PlayerEntity;
import jp.co.taxis.funsite.form.SearchForm;
import jp.co.taxis.funsite.service.PlayerService;

@Controller
@RequestMapping("player")
public class PlayerSearchController {

	/** playerサービス */
	@Autowired
	private PlayerService playerService;

	/** 検索→表示の画面表示 */
	@RequestMapping("search")
	public String searchDisplay(@ModelAttribute("name") SearchForm searchForm,Model model) {

		List<PlayerEntity> playerList = playerService.selectAll();
		model.addAttribute("playerList", playerList);
		return "search";
	}
	
	@RequestMapping("test")
	public String seatchTest(@ModelAttribute("name") @Validated SearchForm searchForm, BindingResult result,Model model) {
		
		if(result.hasErrors()) {
			return "search";
		}
		
		List<PlayerEntity> playerList = playerService.selectLikeName(searchForm.getName());
		model.addAttribute("playerList",playerList);
		return "search";
	}

}
