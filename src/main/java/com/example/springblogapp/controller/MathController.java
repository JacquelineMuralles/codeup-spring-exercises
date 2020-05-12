package com.example.springblogapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller
public class MathController {
    //method that listens for
    //    /{operation}/{firstnum}/{keyword}/{secondnum}

//        @GetMapping("/{opperation}/{a}/{keyword}/{b}")
//        @ResponseBody
//    public String addition(@PathVariable String operation, @PathVariable int a, @PathVariable String keyword, @PathVariable int b){
//            // decide what to do based on "add" "subtract"...etc from 'operation' PathVariable
//            switch (operation){
//                case "add":
//                    int sum;
//                    sum = a + b;;
//                    return Integer.toString(sum);
//                case "subtract":
//                    int diff;
//                    diff = b - a;
//                    return Integer.toString(diff);
//                case "multiply":
//                    int product;
//                    product = a * b;
//                    return Integer.toString(product);
//                case "divide":
//                    int quotient;
//                    quotient = a / b;
//                    if (a % b != 0){
//                        quotient =
//                    }
//            }
//    }
}
