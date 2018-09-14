package com.terwergreen.bugucms.controller.api.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PostApiController.class)
public class PostApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PostApiController postApiController;

    @Test
    public void getPosts() {
        try {
            given(postApiController.getPosts(null, null, null, null,null, 1, 10));

            mvc.perform(get("all")
                    //.with(user("blaze").password("Q1w2e3r4"))
                    .contentType(APPLICATION_JSON))
                    //.andExpecct(jsonPathonPath("$", hasSize(1)))
                    //.andExpect(jsonPath("$[0].city", is(arrival.getCity())))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}