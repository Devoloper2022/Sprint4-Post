package com.sonik.practicum.service;

import com.sonik.practicum.repository.Interface.PostRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Path;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    @Mock
    private PostRepo postRepository;
    @Mock
    private CommentService commentService;

    @TempDir
    Path tempUploadDir;

    private PostService postService;

    @BeforeEach
    void setUp() {
        postService = new PostService(postRepository, commentService);
    }

    private Post createPost(String title, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setText(content);
        return post;
    }

    @Test
    void saveNewPost () throws Exception{
        Post post = createPost("Title","content");
        MockMultipartFile image = new MockMultipartFile("file","pic.jpg", "image/jpeg",new byte[] {1,2,3});

        when(postRepository.save(any())).thenReturn(1L);
        when(postRepository.findById(1L)).thenReturn(post);
        when(tagRepository.loadTags(1L)).thenReturn(List.of("tag1"));
        when(commentService.loadComments(1L)).thenReturn(List.of());

        Post saved = postServiceImpl.save(post, image, "tag1,tag2");

        assertNotNull(saved.getImagePath());
        assertTrue(saved.getImagePath().endsWith(".jpg"));

        Path path = tempUploadDir.resolve(saved.getImagePath());
        assertTrue(Files.exists(path));
        verify(postRepository).save(any());
        verify(tagRepository).insertTags(eq(1L), eq(List.of("tag1", "tag2")));
    }

    @Test
    void findAllPosts () {
        Post p1 = createPost("Title1", "content1");
        Post p2 =createPost("Title2", "content2");
        p1.setId(1L);
        p2.setId(2L);

        List<Post> all = List.of(p1,p2);
        Paging paging = new Paging();
        paging.setPageNumber(1);
        paging.setPageSize(1);

        when(postRepository.findAll()).thenReturn(all);
        when(tagRepository.loadTags(1L)).thenReturn(List.of("tag1"));
        when(tagRepository.loadTags(2L)).thenReturn(List.of("tag2"));

        List<Post> res = postServiceImpl.findAll(null, paging);
        assertEquals(1, res.size());
        assertEquals("Title1", res.get(0).getTitle());
        assertEquals(List.of("tag1"), res.get(0).getTags());
        assertTrue(paging.isHasNext());

        verify(postRepository).findAll();
        verify(tagRepository).loadTags(1L);
        verify(tagRepository).loadTags(2L);
    }

    @Test
    void updatePost() {
        Post updatePost = createPost("New Title", "Updated text");
        MockMultipartFile image = new MockMultipartFile("file", "newpic.jpg", "image/jpeg", new byte[] { 4, 5, 6 });

        Post expectedPost = createPost("New Title", "Updated text");
        expectedPost.setId(1L);
        expectedPost.setImagePath("newpic.jpg");
        expectedPost.setTags(List.of("newtag1"));
        expectedPost.setComments(List.of());

        when(tagRepository.loadTags(1L)).thenReturn(List.of("newtag1"));
        when(commentService.loadComments(1L)).thenReturn(List.of());
        when(postRepository.findById(1L)).thenReturn(expectedPost);

        Post updated = postServiceImpl.update(1L, updatePost, image, "newtag1");
        assertEquals("New Title", updated.getTitle());
        assertEquals(List.of("newtag1"), updated.getTags());

        verify(postRepository).update(argThat(post -> post.getId().equals(1L) && post.getTitle().equals("New Title")
                && post.getText().equals("Updated text")));
        verify(tagRepository).deleteTags(1L);
        verify(tagRepository).insertTags(eq(1L), eq(List.of("newtag1")));
    }

    @Test
    void findByIdPost() {
        Post post = createPost("Title", "content");
        post.setId(1L);

        when(postRepository.findById(1L)).thenReturn(post);
        when(tagRepository.loadTags(1L)).thenReturn(List.of("tag1", "newtag1"));
        when(commentService.loadComments(1L)).thenReturn(List.of());

        Post res = postServiceImpl.findById(1L);
        assertEquals(1L, res.getId());
        assertEquals("Title", res.getTitle());
        assertEquals(List.of("tag1", "newtag1"), res.getTags());
        assertNotNull(res.getComments());
        verify(postRepository).findById(1L);
        verify(tagRepository).loadTags(1L);
        verify(commentService).loadComments(1L);
    }

    @Test
    void lincrementsLikes() {
        postServiceImpl.like(1L, true);
        verify(postRepository).updateLikes(1L, 1);
    }

    @Test
    void decrementsLikes() {
        postServiceImpl.like(1L, false);
        verify(postRepository).updateLikes(1L, -1);
    }

    @Test
    void deleteByIdPost() {
        postServiceImpl.deleteById(2L);
        verify(postRepository).deleteById(2L);
    }
}
