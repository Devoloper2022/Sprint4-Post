package com.sonik.practicum.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class PostControllerTest {

    @MockitoBean
    private PostController controller;

    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("classpath:templates/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(viewResolver)
                .build();
    }


    @Test
    public void testGetPostsStatus() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }


    @Test
    public void testGetPostStatus() throws Exception {
        mockMvc.perform(get("/1"))
                .andExpect(status().isOk());
    }


    @Test
    public void testAddPostFormStatus() throws Exception {
        mockMvc.perform(get("/add"))
                .andExpect(status().isOk());
    }

    @Test
    public void testEditPostFormStatus() throws Exception {
        mockMvc.perform(get("/1/edit"))
                .andExpect(status().isOk());
    }

//    @Test
//    public void testAddPostStatus() throws Exception {
//        MockMultipartFile file = new MockMultipartFile(
//                "image",
//                "test.png",
//                MediaType.IMAGE_PNG_VALUE,
//                "This is a test file content.".getBytes()
//        );
//
//        mockMvc.perform(multipart("/")
//                        .file(file)
//                        .param("title", "some title")
//                        .param("content", "some text")
//                        .param("tags", "some tags")
//                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
//                .andExpect(status().isOk());
//    }

    @Test
    public void testEditPostStatus() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "image", // Parameter name in the controller
                "test.png",
                MediaType.IMAGE_PNG_VALUE,
                "This is a test file content.".getBytes()
        );

        mockMvc.perform(multipart("/1")
                        .file(file)
                        .param("title", "some title")
                        .param("content", "some text")
                        .param("tags", "some tags")
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isOk());
    }





    @Test
    public void testAddCommentStatus() throws Exception {
        mockMvc.perform(post("/1/comments")
                        .param("comment", "some comment"))
                .andExpect(status().isOk());
    }

    @Test
    public void testEditCommentStatus() throws Exception {
        mockMvc.perform(post("/1/comments/2")
                        .param("comment", "some text"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteCommentStatus() throws Exception {
        mockMvc.perform(post("/1/comments/2/delete"))
                .andExpect(status().isOk());
    }

}
