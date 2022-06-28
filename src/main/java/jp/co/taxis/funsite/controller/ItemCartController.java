package jp.co.taxis.funsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.taxis.funsite.dto.ItemDto;
import jp.co.taxis.funsite.dto.UserDto;

@Controller
@RequestMapping("item")
public class ItemCartController {

	@Autowired
	private UserDto userDto;

	@RequestMapping(value = "cart", method = { RequestMethod.GET, RequestMethod.POST })
	public String itemCartList(Model model) {

		List<ItemDto> itemCartList = userDto.getItemDto();
		model.addAttribute("itemCartList", itemCartList);

		return "cart";
	}

	@RequestMapping(value = "cart_confirm", method = { RequestMethod.GET, RequestMethod.POST })
	public String confirm() {

		return "cart_confirm";
	}

	@RequestMapping(value = "cart_complete", method = { RequestMethod.GET, RequestMethod.POST })
	public String complete() {

		return "cart_complete";
	}

}
