package jp.co.taxis.funsite.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.taxis.funsite.dto.ItemDto;
import jp.co.taxis.funsite.dto.UserDto;
import jp.co.taxis.funsite.entity.ItemEntity;
import jp.co.taxis.funsite.entity.OrderDetailEntity;
import jp.co.taxis.funsite.form.TicketForm;
import jp.co.taxis.funsite.service.GameService;
import jp.co.taxis.funsite.service.ItemService;

@Controller
@RequestMapping("ticket")
public class GameDetailController {

	@Autowired
	private GameService gameService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemDto itemDto;

	@Autowired
	private UserDto userDto;

	/**
	 * 試合詳細情報画面表示メソッド
	 * 
	 * @param gameForm
	 * @param model
	 * @return
	 */
	@RequestMapping("detail")
	public String gameDetail(@RequestParam("id") int id, @ModelAttribute("game") TicketForm ticketForm, Model model) {

		// 試合情報を取得する
		// GameEntity game = new GameEntity();
		// gameService.selectById(id);

		ticketForm.setId(id);

		List<ItemEntity> itemList = itemService.selectTicket(id);

		// 出力
		// model.addAttribute("game", game);
		model.addAttribute("itemList", itemList);

		// Viewの選択
		return "ticket_detail";
	}

	@RequestMapping("sheet")
	public String sheet(Model model, @ModelAttribute("game") TicketForm ticketForm,
			RedirectAttributes redirectAttributes) {
		OrderDetailEntity orderDetailEntity = new OrderDetailEntity();

		orderDetailEntity.setItem(itemService.selectById(ticketForm.getId()));
		orderDetailEntity.setQuantity(ticketForm.getQuantity());
		orderDetailEntity.setOrderDate(LocalDate.now());
		orderDetailEntity.setMember(userDto.getMemberEntity());

		itemDto.setOrderDetailEntity(orderDetailEntity);

		redirectAttributes.addAttribute("id", ticketForm.getId());

		return "redirect:detail";

	}

}
