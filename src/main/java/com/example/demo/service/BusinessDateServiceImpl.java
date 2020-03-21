package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BusinessDate;
import com.example.demo.repository.BusinessDateDao;

@Service
public class BusinessDateServiceImpl implements BusinessDateService {

	@Autowired
	private BusinessDateDao dao;

	@Override
	public void insertSurvey(BusinessDate businessDate) {
		dao.insertDate(businessDate);
	}

	@Override
	public List<BusinessDate> getAll() {

		return dao.getAll();
	}

}
