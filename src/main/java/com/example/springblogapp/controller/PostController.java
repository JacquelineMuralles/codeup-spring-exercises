//package com.example.springblogapp;
//
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
////@Controller
//public class PostController {
//    @GetMapping("/posts")
//    @ResponseBody
//    public String getPosts(){
//        return "<h1>posts index page</h1>";
//    }
//
//    @GetMapping("/posts/{id}")
//    @ResponseBody
//    public String getPostsById(@PathVariable long id){
//        return "Showing individual post id: " + id;
//    }
//
//    @GetMapping("/posts/create")
//    @ResponseBody
//    public String createPost(){
//        return "view the form for creating a post";
//    }
//
//    @PostMapping("/posts/create")
//    @ResponseBody
//    public String submitCreatingPost(){
//        return "create a new post";
//    }
//}
