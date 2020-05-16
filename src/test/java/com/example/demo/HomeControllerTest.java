package com.example.demo;

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


}
