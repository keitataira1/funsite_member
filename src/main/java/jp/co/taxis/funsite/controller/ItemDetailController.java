package jp.co.taxis.funsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.taxis.funsite.service.ItemService;

@Controller
@RequestMapping("item")
public class ItemDetailController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value="detail",method= {RequestMethod.GET,RequestMethod.POST})
	public String detail(@ModelAttribute("item") Model model) {
		
		
		return "detail";	
	
	}

}
