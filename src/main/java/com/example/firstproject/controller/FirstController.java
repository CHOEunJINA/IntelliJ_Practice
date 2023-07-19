package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi") // 접속할 URL 주소 작성해야 연결됨

    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "whswls");
        return "greetings"; // templates/greetings.mustache -> 브라우저로 알아서 전송됨!
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname", "조은진");
        return "goodbye";
    }
}
