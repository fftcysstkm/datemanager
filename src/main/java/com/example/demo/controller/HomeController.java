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
import org.springframework.web.bind.annotation.PathVariable;
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
public class HomeController {
	/**
	 * コントローラー
	 *
	 */
	@Autowired
	private BusinessDateService businessDateService;

	//初回画面。
	@GetMapping()
	public String getHome(@ModelAttribute DateForm dateForm, Model model) {
		//System.out.println(businessDateService.getCount());

		return "index";
	}

	//一覧から「戻る」で初回画面に戻る。
	@PostMapping(params="back")
	public String postBack(@ModelAttribute DateForm dateForm,Model model) {
		return "index";
	}



	@PostMapping(params="insert")
	public String postInsert(@ModelAttribute @Validated DateForm dateForm,
			BindingResult bindingResult,
			Model model) {

		//バリデーションで間違いがあれば、getHomeメソッド呼び出し。
		if(bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			return getHome(dateForm,model);
		}

		//Entityインスタンス作成。Formクラスの値をEntityクラスへ詰め込み。
		BusinessDate businessDate = new BusinessDate();
		businessDate.setDate_id(dateForm.getDate_id());
		businessDate.setBase_date(java.sql.Date.valueOf(dateForm.getBase_date()));
		businessDate.setDate_name(dateForm.getDate_name());
		businessDate.setDiff_year(dateForm.getDiff_year());
		businessDate.setDiff_month(dateForm.getDiff_month());
		businessDate.setDiff_day(dateForm.getDiff_day());

		businessDateService.insertDate(businessDate);

		//System.out.println(businessDate);
		return "redirect:businessdate";
	}

	//一覧表示
	@PostMapping("/datelist")
	public String postIndex(Model model) {
		List<BusinessDate> dateList = businessDateService.getAll();
		model.addAttribute("dateList",dateList);
		return "list";
	}


	//計算シミュレーションメソッド。「計算実行」ボタンで実行される。
	@PostMapping(params="calc")
	public String postSimlate(@ModelAttribute @Validated DateForm dateForm,
			BindingResult bindingResult,
			Model model) {
		//バリデーションで間違いがあれば、getHomeメソッド呼び出し。
		if(bindingResult.hasErrors()) {
			return getHome(dateForm, model);
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
		System.out.println(dateForm);
		return "index";
	}



	//詳細画面のGET用メソッド
	//@PathVariableでURLの指定箇所からパラメータを取得する。
	@GetMapping("/dateDetail/{date_id}")
	public String getDateDetail(@ModelAttribute DateForm dateForm,
			Model model,
			@PathVariable("date_id")String date_id){


			BusinessDate businessDate = businessDateService.selectOne(date_id);
			System.out.println(businessDate);

			dateForm.setId(businessDate.getId());
			dateForm.setDate_id(businessDate.getDate_id());
			dateForm.setBase_date(businessDate.getBase_date().toLocalDate());
			dateForm.setDate_name(businessDate.getDate_name());
			dateForm.setDiff_year(businessDate.getDiff_year());
			dateForm.setDiff_month(businessDate.getDiff_month());
			dateForm.setDiff_day(businessDate.getDiff_day());
			model.addAttribute("dateForm", dateForm);


		return "dateDetail";
	}

}
