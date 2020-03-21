package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.BusinessDateService;



/**
 * @author takumasato
 */



@RequestMapping("/businessdate")
@Controller
public class BusinessDateController {
	/**
	 * コントローラー
	 *
	 */
	@Autowired
	private BusinessDateService businessDateService;

	//あとで作る
	@GetMapping
	public String getHome() {
		return "index";
	}
	//一覧表示
	@PostMapping("/datelist")
	public String postIndex(Model model) {
		return "datelist";
	}

}
