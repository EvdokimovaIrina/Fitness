package ru.jogging.controller;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import ru.jogging.Application;

import static junit.framework.TestCase.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;


import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class JoggingRestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mvc;

    private String userName = "test1";
    private String password = "123";

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    private MvcResult addJogging(String date,String minutes) throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("date", date);
        params.add("minutes", minutes);

        MvcResult result = mvc.perform(post("/jogginglist")
                .params(params).with(httpBasic(userName, password))
                .accept("application/json;charset=UTF-8"))
                .andReturn();
        return result;
    }

    @Test
    public void addJoggingTest() throws Exception {
        String successfulResponse = "{\"eventType\":\"JOGGINDG\",\"mainObject\":{\"id\":1,\"dateJogging\":1523491200000,\"numberOfMinutes\":120.0}}";
        MvcResult result = addJogging("1523491200000","120");
        String resultString = result.getResponse().getContentAsString();
        assertEquals(resultString, successfulResponse);
    }

    @Test
    public void bgetJoggingListTest() throws Exception {
        String successfulResponse = "\"dateJogging\":1523491200000,\"numberOfMinutes\":60.0";
        addJogging("1523491200000","60");
        MvcResult result = mvc.perform(get("/jogginglist")
                .with(httpBasic(userName, password))
                .accept("application/json;charset=UTF-8"))
                .andReturn();
        String resultString = result.getResponse().getContentAsString();
        assertEquals(true, resultString.contains("JOGGINDGLIST")&&resultString.contains(successfulResponse));
    }

    @Test
    public void deleteJogging() throws Exception {
        addJogging("1523491200000","80");
        String successfulResponse = "{\"eventType\":\"SUCCESS\",\"mainObject\":true}";
        String idJogging = "1";
        MvcResult result = mvc.perform(delete("/jogginglist/" + idJogging)
                .with(httpBasic(userName, password))
                .accept("application/json;charset=UTF-8"))
                .andReturn();
        String resultString = result.getResponse().getContentAsString();
        assertEquals(resultString, successfulResponse);

    }
}
