package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class RestApiTodoApplicationTests {

	@Autowired MockMvc mock;
	
	@Test
	void contextLoad() throws Exception {
		assertThat(this.mock).isNotNull();
	}
	@Test
	void createLista() throws Exception {
		this.mock.perform(post("/create/lista/listaTESTE")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	void getLista() throws Exception {
		this.mock.perform(post("/find/lista")).andDo(print()).andExpect(status().isOk());
	}

}
