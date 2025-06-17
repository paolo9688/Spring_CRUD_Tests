package com.example.crud_test_exercise;

import com.example.crud_test_exercise.controller.UserController;
import com.example.crud_test_exercise.entity.User;
import com.example.crud_test_exercise.service.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles(value = "dev")
@AutoConfigureMockMvc
class UserTests {

	@MockitoBean
	private UserService userService;

	@Autowired
	private UserController userController;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private User user;

	@BeforeEach
	public void setUp() throws Exception {
		user = new User("Mario", "Rossi", true);
		user.setId(1L);

		mockMvc.perform(post("/api/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user)));
	}

	@Test
	void contextLoads() {
		assertThat(userController).isNotNull();
	}

	// test per crea un nuovo utente:
	@Test
	public void createUserTest() throws Exception{
		when(userService.createUser(any(User.class))).thenReturn(user);

		mockMvc.perform(post("/api/user/create-user")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value(user.getName()))
				.andDo(print());
	}

	// test per trova tutti gli utenti:
	@Test
	public void getUsersTest() throws Exception{
		when(userService.getUsers()).thenReturn(List.of(user));

		mockMvc.perform(get("/api/user/find-users")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}

	// test per trova un utente per id:
	@Test
	public void getUserByIdTest() throws Exception{
		when(userService.getUserById(user.getId())).thenReturn(Optional.of(user));

		mockMvc.perform(get("/api/user/find-user-by-id/" + user.getId())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}
}
