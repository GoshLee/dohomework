package com.helper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HandlerController {


    @GetMapping({"/", "index"})
    public String index(){
        return "index";
    }

    @GetMapping("/teacherLogin")
    public String teacherLogin(){
        return "teacherLogin";
    }

    @GetMapping({"/login"})
    public String loginMethod(){
        return "login";
    }


    @GetMapping("/stuLogin")
    public String stuLogin(){
        return "stuLogin";
    }

    @GetMapping("/adminLogin")
    public String adminLogin(){
        return "adminLogin";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/teacher")
    public String teacher(){
        return "teacher";
    }

    @GetMapping("/barrage")
    public String barrage(){
        return "barrage";
    }
}
