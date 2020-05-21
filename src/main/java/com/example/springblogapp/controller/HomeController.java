package com.example.springblogapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String landing() {
        return "/home";
    }

    @GetMapping("/home")
    public String goHome(){
        return "home";
    }

//    @GetMapping("/hello/{name}")
//    public String sayHello(@PathVariable String name, Model model) {
//        model.addAttribute("name", name);
//        return "hello";
//    }
//
//    @GetMapping("/join")
//    public String showJoinForm() {
//        return "join";
//    }
//
//    @PostMapping("/join")
//    public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model) {
//        model.addAttribute("cohort", "Welcome to " + cohort + "!");
//        return "join";
//    }
//
//    @GetMapping("/welcome")
//    public String showWelcome() {
//        return "welcome";
//    }

//    @GetMapping("/roll")
//    public String rollDice(Model model) {
//        model.addAttribute("title", "Roll Dice");
//        return "/roll";
//    }
//
//
//
//    @GetMapping("/roll/{userNum}")
//    public String diceRollResult(@PathVariable int userNum, Model model){
//        String message;
//        int random = (int) Math.floor((Math.random() * 6) + 1);
//        if(userNum == random){
//            message = "Congratulations! You won this round! You chose: " + userNum + " and the dice landed on: " + random + ".";
//        }else {
//            message = "Awwww you lost this round! You chose: " + userNum + " and the dice landed on: " + random + ".";
//        }
//        model.addAttribute("message", message);
//        return "/roll";
//    }

}
