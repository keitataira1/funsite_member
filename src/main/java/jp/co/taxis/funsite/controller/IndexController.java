package jp.co.taxis.funsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("index")
	public String index() {
		// 入力画面を表示するためだけのメソッド
		return "/index";
	}

}
