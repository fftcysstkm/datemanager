package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.BusinessDate;


public interface BusinessDateService {

	int getCount();

	int countDateName(String dateName);

	void insertDate(BusinessDate businessDate);

	List<BusinessDate> getAll();

	BusinessDate selectOne(int id);

	void deleteOne(int id);


}
