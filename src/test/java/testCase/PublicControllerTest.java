package testCase;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.userService.controller.UserController;
import org.userService.entity.Role;
import org.userService.entity.User;
import org.userService.service.UserService;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.modelmapper.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PublicControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;
    @Autowired
    private User user;

    public PublicControllerTest(MockMvc mockMvc, ObjectMapper objectMapper, UserService userService, User user) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.userService = userService;
        this.user = user;
    }

    @Test
    public void testGetUserList() throws Exception {
        when(userService.getAllUsers()).thenReturn((List<User>) user);
        mockMvc.perform(get("/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$", hasSize(1)))
                .andExpect((ResultMatcher) jsonPath("$").isArray())
                .andExpect((ResultMatcher) jsonPath("$").isNotEmpty());
    }

    @Test
    public void testCreateOrder() throws Exception {
        when(userService.saveUser(user)).thenReturn(user);
        mockMvc.perform(
                        post("/create")
                                .content(objectMapper.writeValueAsString(user))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$.firstName", is("andrew")))
                .andExpect((ResultMatcher) jsonPath("$.lastName", is("Vasilyev")))
                .andExpect((ResultMatcher) jsonPath("$.id", is(10)))
                .andExpect((ResultMatcher) jsonPath("$").isNotEmpty());
    }


    @Test
    public void testDeleteOrder() throws Exception {
        User user = new User
                (1L, "andrew", "Vasilyev", "vasilyev@mail.ru",
                        "23.04.1990", Collections.singleton(Role.USER), "pass");
        when(userService.deleteUserById(user.getId())).thenReturn(true);
        mockMvc.perform(delete("/delete/" + user.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
