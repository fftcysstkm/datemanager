package com.example.demo.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BusinessDate;

@Repository
public class BusinessDateDaoImpl implements BusinessDateDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	//一件登録
	@Override
	public void insertDate(BusinessDate businessDate)  throws DataAccessException{
		String sql = "INSERT INTO datetable (date_id, date_name, base_date, diff_year, diff_month, diff_day) VALUES(?, ?, ?, ?, ?, ? , ?)";
		jdbcTemplate.update(sql,businessDate.getDate_id(),businessDate.getDate_name(),businessDate.getBase_date(),businessDate.getDiff_year(),
				businessDate.getDiff_month(),businessDate.getDiff_day());
	}
	//全件取得。Entityクラス(buisnessDate)のListを返す。
	@Override
	public List<BusinessDate> getAll()  throws DataAccessException{

		//DBからMapのListを取得。1件のデータがMapに格納されている。
		List<Map<String, Object>> getList = jdbcTemplate.queryForList("SELECT * FROM datetable");

		//結果返却用のEntityクラスのList<BusinessDate>を用意
		List<BusinessDate> dateList = new ArrayList<BusinessDate>();

		//List<Map>(DBから取得の)の中身を結果返却用のList<BusinessDate>に詰め替える。
		for (Map<String, Object>map: getList) {
			//BusinessDate型インスタンスを用意
			BusinessDate businessDate = new BusinessDate();

			//Entityへ詰める。
			businessDate.setId((int)map.get("id"));
			businessDate.setDate_id((String)map.get("date_id"));
			businessDate.setDate_name((String)map.get("date_name"));
			businessDate.setBase_date((Date)map.get("base_date"));
			businessDate.setDiff_year((int)map.get("diff_year"));
			businessDate.setDiff_month((int)map.get("diff_month"));
			businessDate.setDiff_day((int)map.get("diff_day"));
			dateList.add(businessDate);
		}

		return dateList;
	}

	@Override
	public void deleteDate(int id) throws DataAccessException{

	}

}
