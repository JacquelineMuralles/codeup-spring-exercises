package com.example.springblogapp.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller//dictates that this class is a controller in (MVC)
public class HelloController {
    @PostMapping("/upload")
    public void upload() {
        // do something here for uploading
    }

    @PostMapping("/upload/profile")
    public void uploadProfile() {
        // do something here for uploading
    }

    // *** PATH VARIABLES *** //

    @GetMapping("/hi/{name}")
    @ResponseBody
    public String sayHi(@PathVariable String name) {
        return "Hi, " + name;
    }

    @GetMapping("/defined-ad/{id}/{version}")
    @ResponseBody
    public String showAd(@PathVariable int version, @PathVariable long id) {
        return "showing details for ad with id: " + id + ". Version: " + version;
    }

    // *** Added from views demo *** //


    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping(path = "/increment/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String addOne(@PathVariable int number) {
        return number + " plus one is " + (number + 1) + "!";
    }


    @GetMapping("/join")
    public String showJoinForm() {
        return "join";
    }


    @PostMapping("/join")
    public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model) {
        model.addAttribute("cohort", "Welcome to " + cohort + "!");
        return "join";
    }

    @GetMapping("/welcome")
    public String showWelcome() {
        return "welcome";
    }

}
