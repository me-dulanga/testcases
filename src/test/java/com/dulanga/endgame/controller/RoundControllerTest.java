package com.dulanga.endgame.controller;

import com.dulanga.endgame.model.Round;
import com.dulanga.endgame.service.RoundService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class RoundControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RoundService roundService;

    @Test
    void testGreeting() {
        try {
            this.mockMvc.perform(get("/greeting"))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(content().string(containsString("Hello World")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @WithMockUser(username = "user")
    void testPlay(){
        Round round = new Round();
        Mockito.when(roundService.play("user", 5.0)).thenReturn(round);
        try {
            this.mockMvc.perform(get("/play?bet=5.0"))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(content().string(containsString("userStartBalance")));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
//    @Test
//    void postTest(){
//        String jsonbody = "{test:test}";
//        this.mockMvc.perform(
//                        post("/playround")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(jsonbody)
//                                .header(HttpHeaders.AUTHORIZATION,
//                                        "Basic " + Base64Utils.encodeToString("anna:1".getBytes()))
//                )
//                .andDo(print()).andExpect(status().isBadRequest())
//                .andExpect(content().string(containsString("Maximum bet amount should not be greater than 10")));
//
//    }
}