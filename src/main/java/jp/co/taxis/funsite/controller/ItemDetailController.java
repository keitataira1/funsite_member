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
	public String detail(@RequestParam("id") int id, @RequestParam("message") String message, Model model) {
		
		ItemEntity item = itemService.selectById(id);
		model.addAttribute(item);
		model.addAttribute(message);
		
		return "item_detail";	
	
	}
	
	@RequestMapping("complete")
	public String complete(@ModelAttribute("count") int count, @ModelAttribute("item") ItemEntity item, RedirectAttributes redirectAttributes) {
		

		OrderDetailEntity orderItem = new OrderDetailEntity();
		
		orderItem.setItem(itemService.selectById(item.getId()));
		orderItem.setMember(userDto.getMemberEntity());
		orderItem.setOrderDate(LocalDate.now());
		orderItem.setQuantity(count);
		
		itemDto.setOrderDetailEntity(orderItem);
		
		redirectAttributes.addAttribute("id", item.getId());
		redirectAttributes.addAttribute("message", "カートに保存されました");
		
		return "redirect:detail";
	}

}
