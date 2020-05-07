package com.example.demo;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.BusinessDateDao;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BusinessDateDaoImplTest {
	@Autowired
	BusinessDateDao dao;

	@Test
	public void カウントメソッドの結果が3件である事() {
		assertThat(dao.getCount(),is(3));
	}


}
