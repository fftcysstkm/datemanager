package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.BusinessDate;


public interface BusinessDateService {
	//登録件数取得
	int getCount();
	//種類別の日付名の数を取得
	int countDateName(String dateName);
	//1件登録
	void insertDate(BusinessDate businessDate);
	//一覧取得
	List<BusinessDate> getAll();
	//1件取得（詳細画面用）
	BusinessDate selectOne(int id);
	//１件削除
	void deleteOne(int id);
	//1件編集
	void updateOne(BusinessDate businessDate);

}
