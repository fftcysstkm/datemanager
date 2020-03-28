package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.BusinessDate;
import com.example.demo.form.DateForm;
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

	//初回画面。ラジオボタンで計算式を選択する。
	@GetMapping
	public String getHome(@ModelAttribute DateForm dateForm, Model model) {
		return "index";
	}

	//計算シミュレーションメソッド
	@PostMapping
	public String postSimlate(@ModelAttribute @Validated DateForm dateForm,
			BindingResult bindingResult,
			Model model) {
		//バリデーションに間違いがあれば、getHomeメソッド呼び出し。
		if(bindingResult.hasErrors()) {
			return getHome(dateForm,model);
		}
		System.out.println(dateForm);

		return "index";
	}


	//一覧表示
	@PostMapping("/datelist")
	public String postIndex(Model model) {
		List<BusinessDate> dateList = businessDateService.getAll();
		model.addAttribute("dateList",dateList);
		return "list";
	}

}
