package com.br.titcs.controller;

import java.io.Serializable;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.titcs.AppnatorApplication;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppnatorApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
public abstract class TestBase <T>{

	@Autowired
	protected MockMvc mockMvc;

	protected String mapperObjectToJson(Serializable object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String  stringJson = ow.writeValueAsString(object);

		return stringJson;
	}
	
	protected String mapperObjectToJson(List<T> object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String  stringJson = ow.writeValueAsString(object);

		return stringJson;
	}	
}
