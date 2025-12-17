//package org.launchcode.hello_spring.controllers;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@ResponseBody
//@RequestMapping("hello")
//public class HelloController {
//
//    //Handles request at path /hello
////    @GetMapping("hello")
////    @ResponseBody
////    public String hello(){
////        return "Hello World!";
////    }
//
//    //lives/hello/goodbye
//    @GetMapping("goodbye")
//    public String goodbye(){
//        return "Goodbye, Spring!";
//    }
//
//    //live/hello/hello
//    //Handles request of the form /hello?name=LaunchCode
//    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "hello")
//    public String hellowWithQueryParam(@RequestParam String name){
//        return "Hello, " + name + "!";
//    }
//    //Handles requests  of the form /hello/LaunchCode
//    @GetMapping("{name}")
//
//    public String hellowWithPathVariable(@PathVariable String name){
//        return "Hello, " + name + "!";
//    }
//
//    @GetMapping("form")
//
//    public String helloForm(){
//        return "<html>" +
//                "<body>" +
//                "<form action='hello' method='post'>" +
//                "<input type='text' name='name'>" +
//                "<input type='submit' value='Greet me!'>"+
//                "</form>" +
//                "</body>" +
//                "</html>";
//    }
//}
