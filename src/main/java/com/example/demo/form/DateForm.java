package com.example.demo.form;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DateForm {
	//フォームにはないがシミュレーション表示用に残すフィールド。
	private int id;
	private String date_name;
	private String date_id;

	//@NotBlank
	//@DateTimeFormat(pattern="yyyyMMdd")
	private LocalDate base_date;

	//計算シミュレーション結果を格納する変数。
	private LocalDate calc_date;

	private int diff_year;
	private int diff_month;
	private int diff_day;

	//getterとsetterはlombokの機能により提供される。
}
