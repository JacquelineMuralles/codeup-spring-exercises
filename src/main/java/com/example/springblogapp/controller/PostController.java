package com.example.springblogapp.controller;

import com.example.springblogapp.model.Post;
import com.example.springblogapp.model.User;
import com.example.springblogapp.repositories.PostRepository;
import com.example.springblogapp.repositories.UserRepository;
import com.example.springblogapp.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    // These two next steps are often called dependency injection,
    //      where we create a Repository instance
    //      and initialize it in the controller class constructor.

    //Dependency Injection (brings jpa repo over and acts like the dao)
    private PostRepository postRepo;
    private UserRepository userRepo;
    private EmailService emailService;

    public PostController(UserRepository userRepo, PostRepository postRepo, EmailService emailService){//controller class constructor
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.emailService = emailService;
    }

//    @GetMapping("/posts")//this gets typed in url after localhost8080

    //shows all posts
//    public String showPosts (Model model){//method to show all post when going to /posts
//        ArrayList<Post> posts = new ArrayList<>();//make a new array list to add posts to
//        Post post = new Post();// make a new post
//        post.setBody("He's stinky but he's such an awesome caring pup!");//set body
//        post.setTitle("I Love My Dog!");//set title
//        Post postTwo = new Post("I Love Tacos", "They are delicious and are the most magical things on earth!");// make a second post
//        posts.add(post);//add the two posts to the list
//        posts.add(postTwo);
//        List<Post> postList = postRepo.findAll();
//
//        model.addAttribute("posts", postList);// set the attribute of posts to the posts list
//
//        return "posts/index";// return the index html from the posts directory
//
//    }


//    @GetMapping ("/posts")
//    public String showPostIndex(Model model){
//        List <Post> postList = postRepo.findAll();
//        model.addAttribute("posts", postList);
//        return "posts/index";
//    }
//****************************SHOW ALL POSTS*****************************

    @GetMapping("/posts")
    public String showPostsIndexPage(Model model) {
        model.addAttribute("posts", postRepo.findAll());
        return "posts/index";
    }

//    @GetMapping("/post")
//
//    //shows individual post
//    public String showPost (Model model){
//        Post post = new Post();// make a post
//        post.setBody("body goes here");//set body
//        post.setTitle("title goes here");// set title
//        model.addAttribute("post", post);//give the post attribute the value of the post that you made
//        return "posts/show";// return the html page show from the posts directory
//    }

//*****************************SHOW INDIVIDUAL POST BY ID***********************
    @GetMapping("/posts/{id}")
    public String showIndividualPost(@PathVariable long id, Model model){
        Post onePost = postRepo.getOne(id);//uses JpaRepository.getOne, but for post object.
        model.addAttribute("post", onePost);
        return "posts/show";
    }

//********************************SENDS USER TO A FORM TO CREATE A POST***************
    @GetMapping("/posts/create")
    public String createPost(Model model){
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (obj == null || !(obj instanceof UserDetails)) {
            return "redirect:/login";
        }
        User user = (User) obj;
        Post post = new Post();
        post.setUser(user);
        model.addAttribute("post", post);
        return "posts/create";
    }

//*******************************CREATES A NEW POST FROM THE FILLED OUT FORM*****************
    @PostMapping("/posts/create")
    public String submitCreatePost(@ModelAttribute Post post) {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (obj == null || !(obj instanceof UserDetails)) {
            return "redirect:/login";
        }
        User user = (User) obj;
        post.setUser(user);
        postRepo.save(post);
        emailService.prepareAndSend(post, "CREATED Post: " + post.getTitle(),
                post.getTitle() +"\n\n" +
                        post.getBody());
        return "redirect:/posts/" + post.getId();
    }

//*******************************TAKES USER TO EDIT PAGE FOR POST BY ID***************************
    @GetMapping("/posts/{id}/edit")
    public String getEditPostForm(@PathVariable long id, Model model){
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (obj == null || !(obj instanceof UserDetails)) {
            return "redirect:/login";
        }
        User user = (User) obj;
        Post post = postRepo.getOne(id);
        if (post.getUser().getId() != user.getId()) {
            return "redirect:/posts/" + post.getId();
        }
        model.addAttribute("post", post);
        return "/posts/edit";
    }

//******************TAKES EDITS FROM EDIT PAGE AND IMPLEMENTS THEM FOR THE POST OF THAT ID************
    @PostMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, @ModelAttribute Post post) {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (obj == null || !(obj instanceof UserDetails)) {
            return "redirect:/login";
        }
        User user = (User) obj;
        post.setId(id);
        post.setUser(user);
        postRepo.save(post);
        emailService.prepareAndSend(post, "EDITED post: " + post.getTitle(),
                post.getTitle() +"\n\n" +
                        post.getBody());
        return "redirect:/posts/" + post.getId();
    }

//******************************TAKES USER TO DELETE POST FORM FOR THE POST WITH THAT ID*************************
//    @GetMapping("/posts/{id}/delete")
//    public String getDeletePostForm(@PathVariable long id, Model model){
//        Post aPost = postRepo.getOne(id);
//        model.addAttribute("post", aPost);
//        return "posts/delete";
//    }

//*******************************TAKES FORM AND DELETES THE POST OF THE PASSED IN ID**********************
//    @PostMapping("/posts/{id}/delete")
//    public String deletePost(@PathVariable long id){
//        Post aPost = postRepo.getOne(id);
//        postRepo.delete(aPost);
//        return "redirect:/posts";
//    }

    @GetMapping("/posts/editcreate")
    public String editCreatePost(Model model) {
    Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (obj == null || !(obj instanceof UserDetails)) {
        return "redirect:/login";
    }
    User user = (User) obj;
    Post post = new Post();
        post.setUser(user);
        model.addAttribute("post", post);
        return "/posts/edit";
}

    @PostMapping("/posts/{id}/delete")
    public String deleteAd(@PathVariable long id, Model model) {
        postRepo.deleteById(id);
        return "redirect:/posts";
    }
}
