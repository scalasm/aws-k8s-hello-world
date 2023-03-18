package me.marioscalas.helloworld;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.hamcrest.MockitoHamcrest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest( HelloWorldController.class )
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getSalute() throws Exception {
        // given no special pre-conditions

        // when
        mvc.perform( get("/hello")
                    .accept( MediaType.APPLICATION_JSON)
                )
        // then
                .andExpect(status().isOk())
                .andExpect(
                    jsonPath("$.salute", containsString("Hello Kubernetes World"))
                );
    }
}
