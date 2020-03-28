package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

	private Map<String,Integer> radioYear;
	private Map<String,Integer> radioMonth;
	private Map<String,Integer> radioDay;

	//初回画面ラジオボタン初期化用メソッド。Year,Month,Dayに応じたMapを返却
	private Map<String, Integer> initRadioYMD(String YearMonthDay){
		Map<String,Integer> radio = new LinkedHashMap<String, Integer>();
		if (YearMonthDay == "Year") {
			radio.put("計算なし",0);
			radio.put("来年",1);
		}else if(YearMonthDay == "Month"){
			radio.put("計算なし", 0);
			radio.put("来月", 1);
		}else {
			radio.put("計算なし",0);
			radio.put("月末",1);
		}
		return radio;
	}

	//初回画面。ラジオボタンで計算式を選択する。
	@GetMapping
	public String getHome(@ModelAttribute DateForm dateForm, Model model) {
		radioYear = initRadioYMD("Year");
		radioMonth = initRadioYMD("Month");
		radioDay = initRadioYMD("Day");
		model.addAttribute("radioYear",radioYear);
		model.addAttribute("radioMonth",radioMonth);
		model.addAttribute("radioDay",radioDay);
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
		//System.out.println(dateForm);

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
