package com.javatraineeprogram.homework5.exception;

import com.javatraineeprogram.homework5.controller.UserController;
import com.javatraineeprogram.homework5.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest(UserController.class)
class ExceptionHandlerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Mock
    private UserController userController;

//    @Test
//    void handleGetUserException() throws Exception {
//        when(userService.getByEmail(anyString())).thenThrow(new GetUserException("Hello world"));
//        this.mockMvc.perform(get("/user/by-email?email=dayhana@gmail.com"))
//                .andExpect(status().isBadRequest())
//                .andExpect(
//                        content()
//                                .json("{\"status\":400,\"message\":\"Hello world\"}")
//                );
//    }
}
