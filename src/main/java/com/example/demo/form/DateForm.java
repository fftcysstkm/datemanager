package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class DateForm {
	//フォームにはないがシミュレーション表示用に残すフィールド。
	private int id;
	private String date_name;



	//計算基準日付
	@NotBlank
	@Pattern(regexp="(19[0-9]{2}|20[0-9]{2})(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])")
	private String base_date;

	//計算シミュレーション結果を格納する変数。
	private String calc_date;

	//日付計算判定用フィールド。来年、来月、月末。
	private int diff_year;
	private int diff_month;
	private int diff_day;


	//getterとsetterはlombokの機能により提供される。
}
