package com.example.demo.entity;



import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="datetable")
public class BusinessDate {
	@Id
	private int id;
	private String date_name;

	private Date base_date;
	private int diff_year;
	private int diff_month;
	private int diff_day;

	//getterとsetterはlombokの機能により提供される。
}
