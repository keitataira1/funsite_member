package jp.co.taxis.funsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.taxis.funsite.dto.ItemDto;
import jp.co.taxis.funsite.dto.UserDto;
import jp.co.taxis.funsite.entity.MemberEntity;

@Controller
@RequestMapping("item")
public class ItemCartController {

	@Autowired
	private UserDto userDto;

	@RequestMapping(value = "cart", method = { RequestMethod.GET, RequestMethod.POST })
	public String itemCartList(Model model) {

		MemberEntity member = userDto.getMemberEntity();
		int price = 0;
		for (ItemDto item : userDto.getItemDtoList()) {
			price += item.getItemEntity().getPrice();
		}
		model.addAttribute("price", price);
		model.addAttribute("itemCartList", userDto.getItemDtoList());
		model.addAttribute("member", member);

		return "cart";
	}

	@RequestMapping(value = "cart_confirm", method = { RequestMethod.GET, RequestMethod.POST })
	public String confirm(Model model) {
		
		model.addAttribute("itemCartList", userDto.getItemDtoList());
		int price = 0;
		for (ItemDto item : userDto.getItemDtoList()) {
			price += item.getItemEntity().getPrice();
		}
		model.addAttribute("price", price);

		return "cart_confirm";
	}

	@RequestMapping(value = "cart_complete", method = { RequestMethod.GET, RequestMethod.POST })
	public String complete() {

		return "cart_complete";
	}

}
