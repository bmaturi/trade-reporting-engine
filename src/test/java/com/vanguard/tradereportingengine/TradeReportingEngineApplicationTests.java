package com.vanguard.tradereportingengine;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TradeReportingEngineApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	// Integration test to make sure the app runs without exception
	// and returns an Ok status
	@Test
	void executeProgram() throws Exception {
		mockMvc.perform(get("/execute").accept(MediaType.ALL)).andExpect(status().isOk());
	}

}
