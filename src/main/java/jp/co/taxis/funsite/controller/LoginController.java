package jp.co.taxis.funsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.taxis.funsite.dto.UserDto;
import jp.co.taxis.funsite.form.LoginForm;

@Controller
@RequestMapping("user")
public class LoginController {

	@Autowired
	private UserDto userDto;

	@GetMapping("login")
	public String login(@ModelAttribute("login") LoginForm loginForm) {
		// 入力画面を表示するためだけのメソッド
		return "user/login";
	}

	@PostMapping("login_process")
	public String loginProcess(@ModelAttribute("login") @Validated LoginForm loginForm, BindingResult result) {
		if (result.hasErrors()) {
			return "user/login";
		}

		return "index.html";
	}
}