package com.sonik.practicum.service;

import com.sonik.practicum.dto.CommentDto;
import com.sonik.practicum.dto.LikeDto;
import com.sonik.practicum.dto.PostsDto;
import com.sonik.practicum.models.Entity.Post;
import com.sonik.practicum.repository.Interface.PostRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepo postRepo;

    @Mock
    private CommentService commentService;

    @BeforeEach
    void setUp() throws Exception {
        // вручную установить значение storePath через reflection
        Field field = PostService.class.getDeclaredField("storePath");
        field.setAccessible(true);
        field.set(postService, "/tmp/");
    }


    @Test
    void testFindAll() {
        Post post = new Post(1L, "title", "text", "tag", 0,"img.jpg");
        List<CommentDto> comments = List.of(new CommentDto());

        when(postRepo.findAll()).thenReturn(List.of(post));
        when(commentService.findAll(1L)).thenReturn(comments);

        List<PostsDto> result = postService.findAll();

        assertEquals(1, result.size());
        assertEquals("title", result.get(0).getTitle());
    }

    @Test
    void testFindById() {
        Post post = new Post(1L, "title", "text", "tag", 3,"img.jpg" );
        when(postRepo.findById(1L)).thenReturn(post);
        when(commentService.findAll(1L)).thenReturn(List.of());

        PostsDto dto = postService.findById(1L);

        assertEquals("title", dto.getTitle());
    }

    @Test
    void testLikeIncrement() {
        Post post = new Post(1L, "title", "text", "tag", 0,"img.jpg");
        when(postRepo.findById(1L)).thenReturn(post);

        postService.like(new LikeDto(true), 1L);

        assertEquals(1, post.getLikes());
        verify(postRepo).update(post);
    }

    @Test
    void testLikeDecrement() {
        Post post = new Post(1L, "title", "text", "tag", 2,"img.jpg");
        when(postRepo.findById(1L)).thenReturn(post);

        postService.like(new LikeDto(false), 1L);

        assertEquals(1, post.getLikes());
        verify(postRepo).update(post);
    }

    @Test
    void testSaveWithImage() throws IOException {
        MultipartFile file = mock(MultipartFile.class);
        when(file.isEmpty()).thenReturn(false);
        when(file.getOriginalFilename()).thenReturn("test.jpg");

        doAnswer(inv -> {
            File dest = inv.getArgument(0);
            try (FileOutputStream out = new FileOutputStream(dest)) {
                out.write("img".getBytes());
            }
            return null;
        }).when(file).transferTo(any(File.class));

        postService.save("Title", "Text", "Tag", file);

        verify(postRepo).save(any(Post.class));
    }


    @Test
    void testDeleteById() {
        postService.deleteById(123L);
        verify(postRepo).deleteById(123L);
    }



}
