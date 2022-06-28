/**
 * 
 */
package jp.co.taxis.funsite.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.taxis.funsite.entity.SupportMessageEntity;
import jp.co.taxis.funsite.exception.ApplicationException;
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
	
	@Autowired
	private MessageSource messageSource;
	
	/**
	 *  応援メッセージ一覧表示画面 */
	
	@RequestMapping(value = "list",method= {RequestMethod.GET,RequestMethod.POST })
	public String messageList(Model model) {
		
		try {
			List<SupportMessageEntity> supportMessageList=supportMessageService.selectAll();
			model.addAttribute("supportMessageList",supportMessageList);
		} catch (ApplicationException e) {
			String messageKey = e.getMessage();
			String message = messageSource.getMessage(messageKey, null, Locale.getDefault());
			model.addAttribute("message", message);
			return "list";
		}
		
		return "list";
	}
	

}
