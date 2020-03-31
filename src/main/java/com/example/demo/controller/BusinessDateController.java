package com.example.demo.controller;

import java.time.temporal.TemporalAdjusters;
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

	//初回画面。
	@GetMapping
	public String getHome(@ModelAttribute DateForm dateForm, Model model) {
		//System.out.println(businessDateService.getCount());
		return "index";
	}

	//計算シミュレーションメソッド
	@PostMapping
	public String postSimlate(@ModelAttribute @Validated DateForm dateForm,
			BindingResult bindingResult,
			Model model) {
		//バリデーションで間違いがあれば、getHomeメソッド呼び出し。
		if(bindingResult.hasErrors()) {
			return getHome(dateForm,model);
		}


		//Formクラスにデータ設定
		//idとbase_date
		dateForm.setId(businessDateService.getCount() + 1);

		//日付ID・日付名・計算結果の日付
		if(dateForm.getDiff_year() == 1) {
			dateForm.setDate_id("NY" + dateForm.getId());
			dateForm.setDate_name("来年");
			dateForm.setCalc_date(dateForm.getBase_date().plusYears(1));
		}else if(dateForm.getDiff_month() == 1) {
			dateForm.setDate_id("NM"  + dateForm.getId());
			dateForm.setDate_name("来月");
			dateForm.setCalc_date(dateForm.getBase_date().plusMonths(1));
		}else {
			dateForm.setDate_id("LD"  + dateForm.getId());
			dateForm.setDate_name("月末");
			dateForm.setCalc_date(dateForm.getBase_date().with(TemporalAdjusters.lastDayOfMonth()));
		}
		model.addAttribute("dateForm", dateForm);
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
