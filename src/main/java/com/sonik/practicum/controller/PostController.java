package com.sonik.practicum.controller;

import com.sonik.practicum.models.Post;
import com.sonik.practicum.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class PostController {
    private final PostService service;

    public PostController(PostService postService) {
        this.service = postService;
    }


    @GetMapping
    public String posts(
//            @RequestParam(defaultValue = "") String search,
//            @RequestParam(defaultValue = "0") int pageNumber,
//            @RequestParam(defaultValue = "10") int pageSize,
            Model model
    ) {
        List<Post> posts = service.findAll();

        model.addAttribute("posts", posts);
//        model.addAttribute("searchQuery", search);
//        model.addAttribute("currentPage", pageNumber);
//        model.addAttribute("totalPages", 5);
//        model.addAttribute("pageSize", pageSize);
        return "post/posts";
    }


    @PostMapping
    public String save(@ModelAttribute Post post) {
        service.save(post);
        return "redirect:/post/blog";
    }



    @GetMapping("/add")
    public String getAddPage() {
        return "post/add-post";
    }



    @GetMapping(value = "/{id}")
    public String get(@PathVariable(name = "id") Long id) {
        service.(id);

        return "redirect:/post/blog";
    }

    @PostMapping(value = "/{id}")
    public String edit(@ModelAttribute Post post) {
        service.update(post);

        return "redirect:/post/blog/" + post.getId();
    }
//




    @PostMapping(value = "/{id}", params = "_method=delete")
    public String delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);

        return "redirect:/posts";
    }





}
