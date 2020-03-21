package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.BusinessDate;


public interface BusinessDateService {
	void insertSurvey(BusinessDate businessDate);

	List<BusinessDate> getAll();
}
