package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name="datetable")
public class BusinessDate {
	@Id
	private int id;
	private String date_name;
	private String date_id;

	@DateTimeFormat(pattern="yyyyMMdd")
	private Date base_date;
	private int diff_year;
	private int diff_month;
	private int diff_day;

	//getterとsetterはlombokの機能により提供される。
}
