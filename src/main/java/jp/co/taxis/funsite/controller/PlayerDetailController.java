package jp.co.taxis.funsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.taxis.funsite.entity.PlayerEntity;
import jp.co.taxis.funsite.form.PlayerForm;
import jp.co.taxis.funsite.service.PlayerService;

@Controller
@RequestMapping("player")
public class PlayerDetailController {

	@Autowired
	private PlayerService playerService;

	/**
	 * 選手詳細画面表示メソッド
	 * 
	 * @param playerForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail", method = { RequestMethod.POST })
	public String detail(@ModelAttribute("detail") @Validated PlayerForm playerForm, Model model) {

		// 選手情報・トピック情報を取得する
		PlayerEntity player =new PlayerEntity();
		playerService.selectById(player.getId());

		// 出力
		model.addAttribute("player", player);
		
		//Viewの選択
		return "player/detail";
	}

}
