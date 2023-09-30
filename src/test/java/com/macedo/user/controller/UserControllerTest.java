package com.macedo.user.controller;

import com.macedo.user.model.dto.CreateUserDto;
import com.macedo.user.model.dto.UserDTO;
import com.macedo.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<CreateUserDto> createUserDtoJson;
    @Autowired
    private JacksonTester<UserDTO> userDTOJson;
    @MockBean
    private UserService userService;

    @Test
    @DisplayName("Deveria devolver codigo 400 quando informações estão inválidas")
    @WithMockUser
    void usersScenario1() throws Exception {
        var response = mvc.perform(post("/users"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo 201 quando informações estão validas")
    @WithMockUser
    void usersScenario2() throws Exception {

        var userDetails = new UserDTO(null, "Luiza", "luiza@gmail.com");

        when(userService.createUser(any())).thenReturn(userDetails);

        var response = mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createUserDtoJson.write(
                                new CreateUserDto("Luiza", "luiza@gmail.com")
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonExpected = userDTOJson.write(userDetails).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
    }
}