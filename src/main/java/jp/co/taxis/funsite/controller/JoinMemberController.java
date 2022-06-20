package jp.co.taxis.funsite.controller;

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

import jp.co.taxis.funsite.entity.MemberEntity;
import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.form.MemberForm;
import jp.co.taxis.funsite.service.MemberService;

@Controller
@RequestMapping("user")
public class JoinMemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private MessageSource messageSource;

	/**
	 * 会員登録入力画面表示メソッド
	 * 
	 * @param memberForm
	 * @return
	 */
	@RequestMapping(value = "input", method = { RequestMethod.GET })
	public String input(@ModelAttribute("user") MemberForm memberForm) {
		return "input";
	}

	/**
	 * 会員登録入力確認画面表示メソッド
	 * 
	 * @param memberForm
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "confirm", method = { RequestMethod.POST })
	public String confirm(@ModelAttribute("user") @Validated MemberForm memberForm, BindingResult result) {

		if (result.hasErrors()) {
			return "/input";
		}

		return "confirm";
	}

	/**
	 * 会員登録完了画面表示メソッド
	 * 
	 * @return
	 */
	@RequestMapping(value = "complete", method = { RequestMethod.POST })
	public String complete(@ModelAttribute("user") @Validated MemberForm memberForm, BindingResult result, Model model) {
		
		MemberEntity memberEntity = new MemberEntity();
		memberEntity.setMailAddress(memberForm.getMail());
		memberEntity.setPassword(memberForm.getPassword());
		memberEntity.setName(memberForm.getRealName());
		memberEntity.setDisplayName(memberForm.getMemberName());
		memberEntity.setBirthday(memberForm.getBirthday());
		memberEntity.setPostNumber(memberForm.getPostalCode());
		memberEntity.setAddress(memberForm.getAddress());
		memberEntity.setInvalidFlg(true);
		memberEntity.setVersion(1);

		try {
			memberService.insert(memberEntity);
		} catch (ApplicationException e) {
			String messageKey = e.getMessage();
			String message = messageSource.getMessage(messageKey, null, Locale.getDefault());
			model.addAttribute("message", message);
			return "/input";
		}
		
		return "/complete";
	}

}
