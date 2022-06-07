package jp.co.taxis.funsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.taxis.funsite.form.MemberForm;
import jp.co.taxis.funsite.service.MemberService;

@Controller
@RequestMapping("player")
public class JoinMemberController {

	@Autowired
	public MemberService memberService;

	/**
	 * 会員登録入力画面表示メソッド
	 * 
	 * @param memberForm
	 * @return
	 */
	@RequestMapping(value = "user", method = { RequestMethod.GET, RequestMethod.POST })
	public String input(@ModelAttribute("input") MemberForm memberForm) {
		return "input";
	}

	/**
	 * 会員登録確認画面表示メソッド
	 * 
	 * @param memberForm
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "confirm", method = { RequestMethod.POST })
	public String confirm(@ModelAttribute("confirm") @Validated MemberForm memberForm, BindingResult result) {

		if (result.hasErrors()) {
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
