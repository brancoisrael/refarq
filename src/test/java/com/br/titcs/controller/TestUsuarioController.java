package com.br.titcs.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import br.com.titcs.model.UsuarioDTO;

public class TestUsuarioController extends TestBase<UsuarioDTO> {

	@Test
	@WithMockUser(roles = "role1")
	public void testInserir() throws Exception {
		var usuario = new UsuarioDTO();
		usuario.setNome("User 2");
		usuario.setIdade(20);
		
		mockMvc.perform(post("/usuario").contentType(MediaType.APPLICATION_JSON).content(mapperObjectToJson(usuario)))
				.andExpect(status().isOk()).andExpect(content().string(containsString("\"id\":5")));
	}
	
	@Test
	@WithMockUser(roles = "role1")
	public void testAtualizar() throws Exception {
		var usuario = new UsuarioDTO();
		usuario.setId(1L);
		usuario.setNome("User 3");
		usuario.setIdade(20);
		
		mockMvc.perform(put("/usuario").contentType(MediaType.APPLICATION_JSON).content(mapperObjectToJson(usuario)))
				.andExpect(status().isOk()).andExpect(content().string(containsString("\"id\":1")));
	}
	
	@Test
	@WithMockUser(roles = "role1")
	public void testExcluir() throws Exception {
		mockMvc.perform(delete("/usuario/2").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(roles = "role1")
	public void testListarTodos() throws Exception {
		mockMvc.perform(get("/usuario").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string(containsString("\"id\":1")));
	}
	
	@Test
	@WithMockUser(roles = "role1")
	public void testInserirException() throws Exception {
		var usuario = new UsuarioDTO();
		usuario.setIdade(20);
		
		mockMvc.perform(post("/usuario").contentType(MediaType.APPLICATION_JSON).content(mapperObjectToJson(usuario)))
				.andExpect(status().isBadRequest());
	}
}
