package jp.co.taxis.funsite.controller;

import java.time.LocalDate;

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
import jp.co.taxis.funsite.form.CartForm;
import jp.co.taxis.funsite.service.ItemService;

@Controller
@RequestMapping("item")
public class ItemDetailController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemDto itemDto;

	@Autowired
	private UserDto userDto;

	@RequestMapping("detail")
	public String detail(@RequestParam("id") int id, @ModelAttribute("cart") CartForm cartItem, Model model) {

		cartItem.setId(id);
		ItemEntity item = itemService.selectById(id);
		model.addAttribute("item", item);

		return "item_detail";

	}

	@RequestMapping("complete")
	public String complete(@ModelAttribute("cart") CartForm cartItem, RedirectAttributes redirectAttributes) {

		OrderDetailEntity orderItem = new OrderDetailEntity();

		orderItem.setItem(itemService.selectById(cartItem.getId()));
		orderItem.setMember(userDto.getMemberEntity());
		orderItem.setOrderDate(LocalDate.now());
		orderItem.setQuantity(cartItem.getCount());

		itemDto.setOrderDetailEntity(orderItem);

		redirectAttributes.addAttribute("id", cartItem.getId());

		return "redirect:detail";
	}

}
