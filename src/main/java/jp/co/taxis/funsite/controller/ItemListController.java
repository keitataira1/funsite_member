package jp.co.taxis.funsite.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.taxis.funsite.entity.ItemEntity;
import jp.co.taxis.funsite.form.SearchItemForm;
import jp.co.taxis.funsite.service.ItemService;

@Controller
@RequestMapping("item")
public class ItemListController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private MessageSource messageSource;

	/**
	 * 商品一覧画面
	 *
	 * @param itemForm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = { RequestMethod.GET })
	public String list(@ModelAttribute("search") SearchItemForm searchForm,Model model) {

		List<ItemEntity> itemList = itemService.selectAll();
		if (itemList.isEmpty()) {
			String message = messageSource.getMessage("itemList.empty.error", null, Locale.getDefault());
			model.addAttribute("message", message);
		}
		
		model.addAttribute("itemList", itemList);
		
		return "item_list";

	}
}