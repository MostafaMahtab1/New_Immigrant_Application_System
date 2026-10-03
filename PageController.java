// Contributed by Mostafa Mahtab
// Loads approver UI page details so approver can approver/disapprover forms, doesn't need now.
package edu.gmu.cs321.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PageController {

    
    @GetMapping("/")
    public String home() {
        return "index";
    }

}

