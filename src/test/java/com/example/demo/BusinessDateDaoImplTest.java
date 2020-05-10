package com.example.demo;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.BusinessDateDao;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "/application.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class BusinessDateDaoImplTest {

	@Autowired
	BusinessDateDao dao;

	@Test
	@Sql("/testdata.sql")
	public void getCountでデフォルト登録件数2件を取得できる事() throws Exception{

		assertThat(dao.getCount(),is(2));
	}

	@Test(expected = DataAccessException.class)
	public void selectOneで存在しないIDによる１件取得でエラーが発生する事()throws Exception{

		dao.selectOne(999);

	}


}
