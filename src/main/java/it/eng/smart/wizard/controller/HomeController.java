package it.eng.smart.wizard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	@RequestMapping("/home")
	public String hello(@RequestParam(value="msg", required=false, defaultValue="Home") String msg, Model model) {
		model.addAttribute("msg", msg);
		return "home";
	}

}
