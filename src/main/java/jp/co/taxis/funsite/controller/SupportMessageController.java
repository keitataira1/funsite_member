/**
 * 
 */
package jp.co.taxis.funsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.taxis.funsite.entity.SupportMessageEntity;
import jp.co.taxis.funsite.service.SupportMessageService;

/**
 * @author User
 *
 */
@Controller
@RequestMapping("message")
public class SupportMessageController {
	
	@Autowired
	private SupportMessageService supportMessageService;
	
	/**
	 *  応援メッセージ一覧表示画面 */
	
	@RequestMapping(value="list",method= {RequestMethod.GET,RequestMethod.POST })
	public String messageList(@ModelAttribute("message") Model model ) {
		
		List<SupportMessageEntity> supportMessageList=supportMessageService.selectAll();
		model.addAttribute("supportMessageList",supportMessageList);
		return "list";
	}
	

}
