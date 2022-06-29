package jp.co.taxis.funsite.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public String list(@ModelAttribute("search") SearchItemForm searchItemForm,Model model) {

		List<ItemEntity> itemList = itemService.selectAll();
		if (itemList.isEmpty()) {
			String message = messageSource.getMessage("itemList.empty.error", null, Locale.getDefault());
			model.addAttribute("message", message);
		}
		
		model.addAttribute("itemList", itemList);
		
		return "item_list";

	}

	@RequestMapping(value = "/search", method = { RequestMethod.POST })
	public String searchList(@ModelAttribute("search") @Validated SearchItemForm searchItemForm, BindingResult result, Model model) {
		
		
		if (result.hasErrors()) {
			List<ItemEntity> itemList = itemService.selectAll();
			model.addAttribute("itemList", itemList);
			return "item_list";
		}

		List<ItemEntity> itemList = itemService.selectLikeName(searchItemForm.getSearchWord());
		if (itemList.isEmpty()) {
			String message = messageSource.getMessage("itemSearch.empty.error", null, Locale.getDefault());
			model.addAttribute("message", message);
		}

		model.addAttribute("itemList", itemList);

		return "item_list";

	}

}