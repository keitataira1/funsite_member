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
	private UserDto userDto;

	@RequestMapping("detail")
	public String detail(@RequestParam("id") int id, @ModelAttribute("cart") CartForm cartItem, Model model) {

		cartItem.setId(id);
		ItemEntity item = itemService.selectById(id);
		model.addAttribute("item", item);

		return "item_detail";

	}

	@RequestMapping("complete")
	public String complete(@ModelAttribute("cart") CartForm cartItem, RedirectAttributes redirectAttributes,
			Model model) {

		ItemDto itemDto = new ItemDto();
		OrderDetailEntity order = new OrderDetailEntity();
		order.setItem(itemService.selectById(cartItem.getId()));
		order.setMember(userDto.getMemberEntity());
		order.setOrderDate(LocalDate.now());
		order.setQuantity(cartItem.getCount());
		itemDto.setOrderDetailEntity(order);

		ItemEntity item = itemService.selectById(cartItem.getId());
		itemDto.setItemEntity(item);
		
		for(int i= 0;i<cartItem.getCount();i++){	
		userDto.getItemDtoList().add(itemDto);
		}

		model.addAttribute("order", order);

		redirectAttributes.addAttribute("id", cartItem.getId());

		return "redirect:detail";
	}

}
