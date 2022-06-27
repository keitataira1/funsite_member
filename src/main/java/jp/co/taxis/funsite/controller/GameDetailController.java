package jp.co.taxis.funsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jp.co.taxis.funsite.dto.ItemDto;
import jp.co.taxis.funsite.entity.GameEntity;
import jp.co.taxis.funsite.form.GameForm;
import jp.co.taxis.funsite.service.GameService;

@Controller
@RequestMapping("ticket")
@SessionAttributes(names = { "detail" })
public class GameDetailController {

	@Autowired
	private GameService gameService;

	@Autowired
	private ItemDto itemDto;

	/**
	 * 試合詳細情報画面表示メソッド
	 * 
	 * @param gameForm
	 * @param model
	 * @return
	 */
	@RequestMapping("detail")
	public String gameDetail(@RequestParam("id") int id, @ModelAttribute("game") GameForm gameForm,
			SessionStatus sessionStatus, Model model) {

		// 試合情報を取得する
		GameEntity game = new GameEntity();
		gameService.selectById(id);

		// 出力
		model.addAttribute("game", game);

		// Viewの選択
		return "ticket_detail";
	}

	//@RequestMapping("sheet")
	//public String sheet(Model model, @ModelAttribute("game") TicketForm ticketForm) {
	//	OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
	//	GameEntity game = gameService.selectById(ticketForm.getId());
	//	orderDetailEntity.setItem(new ItemEntity(null, "SA席", null, null, null, game));
	//	orderDetailEntity.setQuantity(1);

		// 成功したらセッションに商品を保持する
	//	itemDto.setOrderDetailEntity(orderDetailEntity);
	//	return "redirect:ticket/detail";

	//}

}
