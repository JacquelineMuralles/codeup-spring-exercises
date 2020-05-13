package com.example.springblogapp.controller;


import com.example.springblogapp.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String getPosts(){
        return "<h1>posts index page</h1>";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String getPostsById(@PathVariable long id, Model model){
        Post post = new Post();
        post.setBody("body");
        post.setTitle("title");
        model.addAttribute("post", post);
        return "/posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String viewForm(){
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String submitCreatedPost(){
        return "create a new post";
    }

    @GetMapping("/index")
    @ResponseBody
    public String seeIndex(){
        return "index";
    }

    @GetMapping("/show")
    @ResponseBody
    public String seeShow(){
        return "show";
    }
}
