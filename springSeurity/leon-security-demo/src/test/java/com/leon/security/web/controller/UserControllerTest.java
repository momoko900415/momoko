package com.leon.security.web.controller;

import com.leon.security.web.MainApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Auther: chongwang
 * @Date: 2018/4/23 23:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class UserControllerTest {

    @Autowired
    public WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void hello() throws Exception {
        String contentAsString = mockMvc.perform(get("/user")
                .param("nickName", "momoko")
               // .param("page", "1")
               // .param("size", "15")
               // .param("sort", "age.desc")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);

    }

    @Test
    public void whenFetInfoSuccess() throws Exception {
        mockMvc.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("leon"));

    }

    @Test
    public void whenGetInfoFail() throws Exception {
        String contentAsString = mockMvc.perform(get("/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.userName").value("tom"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);
    }

    @Test
    public void whenCreateSuccess() throws Exception {
        Date date = new Date();
        System.out.println(date.getTime());
        String content = "{\"userName\":\"leon\",\"passWord\":null,\"birthday\":"+date.getTime()+"}";
        String contentAsString = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);

    }

    @Test
    public void whenUpdateSuccess() throws Exception {
        Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println(date.getTime());
        String content = "{\"id\":\"1\",\"userName\":\"leon\",\"passWord\":null,\"birthday\":"+date.getTime()+"}";
        String contentAsString = mockMvc.perform(put("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);

    }

    @Test
    public void whenDeleteSuccess() throws Exception {
        mockMvc.perform(delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void whenUploadSuccess() throws Exception {
        String result = mockMvc.perform(fileUpload("/file")
        .file(new MockMultipartFile("file","test.txt","multipart/form-data","hello update".getBytes("UTF-8"))
        )).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

}