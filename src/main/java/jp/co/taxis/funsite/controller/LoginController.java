package jp.co.taxis.funsite.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import jp.co.taxis.funsite.dto.UserDto;
import jp.co.taxis.funsite.entity.MemberEntity;
import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.form.LoginForm;
import jp.co.taxis.funsite.service.LoginService;

@Controller
@RequestMapping("user")
public class LoginController {

	@Autowired
	private UserDto userDto;
	@Autowired
	private LoginService loginService;
	@Autowired
	private MessageSource messageSource;

	@GetMapping("login")
	public String login(@ModelAttribute("login") LoginForm loginForm) {
		// 入力画面を表示するためだけのメソッド
		return "user/login";
	}

	@PostMapping("login_process")
	public String loginProcess(Model model, @ModelAttribute("login") @Validated LoginForm loginForm,
			BindingResult result) {
		if (result.hasErrors()) {
			return "/user/login";
		}

		MemberEntity memberEntity = new MemberEntity();
		memberEntity.setMailAddress(loginForm.getMailAddress());
		memberEntity.setPassword(loginForm.getPassword());

		try {
			memberEntity = loginService.getMember(memberEntity);
		} catch (ApplicationException e) {
			String messageKey = e.getMessage();
			String message = messageSource.getMessage(messageKey, null, Locale.getDefault());
			model.addAttribute("message", message);
			return "/user/login";
		}

		// 成功したらセッションにメンバーを保持する
		userDto.setMemberEntity(memberEntity);

		return "redirect:../index";
	}

	@GetMapping("logout")
	public String login(HttpSession session, SessionStatus sessionStatus) {
		session.removeAttribute("session");
		session.invalidate();
		sessionStatus.setComplete();
		// 入力画面を表示するためだけのメソッド
		return "redirect:../index";
	}

}