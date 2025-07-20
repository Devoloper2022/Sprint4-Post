package com.sonik.practicum.controller;

import com.sonik.practicum.dto.CommentDto;
import com.sonik.practicum.dto.LikeDto;
import com.sonik.practicum.dto.PostDto;
import com.sonik.practicum.dto.PostsDto;
import com.sonik.practicum.models.Entity.Post;
import com.sonik.practicum.service.CommentService;
import com.sonik.practicum.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class PostController {
    private final PostService service;
    private final CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.service = postService;
        this.commentService = commentService;
    }


    @GetMapping
    public String posts(
//            @RequestParam(defaultValue = "") String search,
//            @RequestParam(defaultValue = "0") int pageNumber,
//            @RequestParam(defaultValue = "10") int pageSize,
            Model model
    ) {
        List<PostsDto> posts = service.findAll();

        model.addAttribute("posts", posts);
//        model.addAttribute("searchQuery", search);
//        model.addAttribute("currentPage", pageNumber);
//        model.addAttribute("totalPages", 5);
//        model.addAttribute("pageSize", pageSize);
        return "post/posts";
    }


    @PostMapping
    public String save(
            @ModelAttribute Post post,
            @RequestParam(name = "image") MultipartFile image) {
        service.save(post,image);
        return "redirect:/post/blog";
    }


    @GetMapping("/add")
    public String getAddPage() {
        return "post/add-post";
    }


    @GetMapping(value = "/{id}")
    public String getPostPage(@PathVariable(name = "id") Long id, Model model) {
        PostsDto post = service.findById(id);
        model.addAttribute("post", post);
        return "post/post";
    }

    @PostMapping(value = "/{id}")
    public String edit(
            @PathVariable(name = "id") Long id,
            @ModelAttribute PostDto post
    ) {
        service.update(post, id);

        return "redirect:/post/blog/" + id;
    }

    @GetMapping(value = "/{id}/edit")
    public String getEditPage(@PathVariable(name = "id") Long id,
                              Model model) {
        PostsDto dto = service.findById(id);
        model.addAttribute("post", dto);
        return "post/add-post";
    }


    @PostMapping(value = "/{id}/delete")
    public String delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);

        return "redirect:/blog";
    }


    @PostMapping(value = "/{id}/like")
    public String likePost(
            @PathVariable(name = "id") Long id,
            @ModelAttribute LikeDto like) {
        service.like(like, id);

        return "redirect:/blog/" + id;
    }

    @PostMapping(value = "/{id}/comments")
    public String addComment(
            @ModelAttribute CommentDto dto,
            @PathVariable(name = "id") Long id) {
        commentService.save(dto, id);
        return "redirect:/blog/" + id;
    }

    @PostMapping(value = "/{id}/comments/{commentsId}")
    public String commentEdit(
            @ModelAttribute CommentDto dto,
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "commentsId") Long commentsId
    ) {
        dto.setId(commentsId);
        commentService.update(dto);
        return "redirect:/blog/" + id;
    }

    @PostMapping(value = "/{id}/comments/{commentsId}/delete")
    public String commentsDelete(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "commentsId") Long commentsId
    ) {
        commentService.deleteById(commentsId);
        return "redirect:/blog/" + id;
    }


}
