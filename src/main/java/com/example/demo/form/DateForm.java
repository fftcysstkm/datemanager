package com.example.demo.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class DateForm {

	//private int id;
	//private String date_name;
	//private String date_id;

	@DateTimeFormat(pattern="yyyyMMdd")
	private Date base_date;
	private int diff_year;
	private int diff_month;
	private int diff_day;

	//getterとsetterはlombokの機能により提供される。
}
