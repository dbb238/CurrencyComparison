package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.model.CurrencyApiModel;
import com.example.demo.model.entity.CurrencyComparison;
import com.example.demo.repository.CurrencyComparisonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private CurrencyComparisonRepository currencyComparisonRepository;

	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setup() {
		CurrencyComparison currencyComparisonEnt = new CurrencyComparison("USD", "美金", "test", "test", "test", "test",
				new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
		currencyComparisonRepository.save(currencyComparisonEnt);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	// unit test 1 測試呼叫查詢幣別對應表資料 API，並顯示其內容
	@Test
	public void testCurrencyComparisonFind() throws Exception {
		mockMvc.perform(get("/api/v1")).andDo(print()).andExpect(status().isOk());
	}

	// unit test 2 測試呼叫新增幣別對應表資料 API。
	@Test
	public void testCurrencyComparisonCreate() throws Exception {
		mockMvc.perform(post("/api/v1").content(asJsonString(new CurrencyApiModel("EUR", "歐元", "testCreate", "testCreate", "testCreate", "testCreate")))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}

	// unit test 3 測試呼叫更新幣別對應表資料 API，並顯示其內容。
	@Test
	public void testCurrencyComparisonEdit() throws Exception {
		mockMvc.perform(put("/api/v1").content(asJsonString(new CurrencyApiModel("USD", "美金", "testEdit", "testEdit", "testEdit", "testEdit")))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}

	// unit test 4  測試呼叫刪除幣別對應表資料 API。
	@Test
	public void testCurrencyComparisonDelete() throws Exception {
		mockMvc.perform(delete("/api/v1").content(asJsonString(new CurrencyApiModel("EUR", "歐元", "testEdit", "testEdit", "testEdit", "testEdit")))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}

	// unit test 5 測試呼叫 coindesk API，並顯示其內容。
	@Test
	public void testCoinApi() throws Exception {
		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
	}

	// unit test 6 測試呼叫資料轉換的 API，並顯示其內容
	@Test
	public void testCoinApiTransform() throws Exception {
		mockMvc.perform(get("/transform")).andDo(print()).andExpect(status().isOk());
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
