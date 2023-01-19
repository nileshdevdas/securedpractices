package com.vinsys.securitylabs.brokenauth.problem;

import java.awt.print.Book;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "brokenauthviewproblem")
@RequestMapping(path = "/brokenauth/problem")
public class BrokenAuthenticationProblemViewController {

	@GetMapping("/session")
	public String addBookView(Model model) {
		model.addAttribute("book", new Book());
		return "session";
	}
}
