package jp.co.taxis.funsite.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.taxis.funsite.entity.GameEntity;
import jp.co.taxis.funsite.service.GameService;

@Controller
@RequestMapping("ticket")
public class GameListController {

	@Autowired
	private GameService gameService;

	@Autowired
	private MessageSource messageSource;

	/**
	 * 試合一覧画面
	 * 
	 * @param gameForm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "ticket/list", method = { RequestMethod.GET })
	public String gameList(Model model) {

		List<GameEntity> gameList = gameService.selectAll();
		
		//DMに試合情報がない場合
		if (gameList.isEmpty()) {
			String message = messageSource.getMessage("list.empty.error", null, Locale.getDefault());
			model.addAttribute("message", message);
		}
		model.addAttribute("gameList", gameList);

		return "ticket/list";
	}

}
