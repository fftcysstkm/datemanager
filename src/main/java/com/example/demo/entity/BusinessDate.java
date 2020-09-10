package com.example.demo.entity;




import org.springframework.data.annotation.Id;

import lombok.Data;

//@Entity
@Data
//@Table(name="datetable")
public class BusinessDate {
	@Id
	private int id;
	private String date_name;

	private String base_date;
	private int diff_year;
	private int diff_month;
	private int diff_day;

	//getterとsetterはlombokの機能により提供される。
}
