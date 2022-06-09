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
	@RequestMapping(value = "input", method = { RequestMethod.GET, RequestMethod.POST })
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
	public String confirm(@ModelAttribute("user") @Validated MemberForm memberForm, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "input";
		}

		MemberEntity memberEntity = new MemberEntity();
		memberEntity.setMailAddress(memberForm.getMail());
		memberEntity.setName(memberForm.getMemberName());

		try {
			memberEntity = memberService.insert(memberEntity);
		} catch (ApplicationException e) {
			String messageKey = e.getMessage();
			String message = messageSource.getMessage(messageKey, null, Locale.getDefault());
			model.addAttribute("message", message);
			return "input";
		}

		return "confirm";
	}

	/**
	 * 会員登録完了画面表示メソッド
	 * 
	 * @return
	 */
	@RequestMapping(value = "complete", method = { RequestMethod.GET })
	public String complete() {
		return "complete";
	}

}
