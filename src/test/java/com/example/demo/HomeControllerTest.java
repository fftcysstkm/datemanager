package com.example.demo;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.BusinessDate;
import com.example.demo.form.DateForm;
import com.example.demo.service.BusinessDateService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

	@MockBean
	BusinessDateService mockDateService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void ホーム画面index_bootが表示される() throws Exception{

		mockMvc.perform(get("/businessdate"))
			.andExpect(status().isOk())
			.andExpect(view().name("index_boot"));
	}

	@Test
	public void 一覧画面list_bootが表示される() throws Exception{

		//Mock化したBusinessDateServiceのgetAll()の戻り値を設定
		List<BusinessDate> dateList = new ArrayList<BusinessDate>();
		dateList.add(createBusinessDate(1, "19990101", "テスト日付", 1, 0, 0));
		when(mockDateService.getAll()).thenReturn(dateList);


		mockMvc.perform(get("/businessdate/datelist"))
			.andExpect(status().isOk())
			.andExpect(view().name("list_boot"))
			.andExpect(model().attribute("dateList",dateList));
	}

	@Test
	public void 計算結果がindex_bootに表示される()throws Exception{

		//Mock化したBisinessDateServiceのgetCount()の戻り値を999に設定
		when(mockDateService.getCount()).thenReturn(999);

		//テスト用のFormクラスをインスタンス化
		DateForm dateForm = createDateForm(1, "19990101", "テスト日付", 1, 0, 0);

		//postリクエスト、計算結果IDが999、計算結果が20000101を検証
		mockMvc.perform(post("/businessdate")
				.param("calc", "calc")
				.flashAttr("dateForm", dateForm))
		.andExpect(status().isOk())
		.andExpect(view().name("index_boot"))
		.andExpect(content().string(containsString("999")))
		.andExpect(content().string(containsString("20000101")))
		.andExpect(model().attribute("dateForm",dateForm));

	}

	//テスト用のEntityクラスの作成メソッド
	public BusinessDate createBusinessDate(int id,String base_date,String date_name,int diff_year,int diff_month,int diff_day) {
		BusinessDate businessDate = new BusinessDate();
		businessDate.setId(id);
		businessDate.setBase_date(base_date);
		businessDate.setDate_name(date_name);
		businessDate.setDiff_year(diff_year);
		businessDate.setDiff_month(diff_month);
		businessDate.setDiff_month(diff_day);

		return businessDate;
	}

	//テスト用のFormクラスの作成メソッド
	public DateForm createDateForm(int id,String base_date,String date_name,int diff_year,int diff_month,int diff_day) {
		DateForm dateForm = new DateForm();
		dateForm.setId(id);
		dateForm.setBase_date(base_date);
		dateForm.setDate_name(date_name);
		dateForm.setDiff_year(diff_year);
		dateForm.setDiff_month(diff_month);
		dateForm.setDiff_month(diff_day);
		//dateForm.setCalc_date(calc_date);

		return dateForm;
	}


}
