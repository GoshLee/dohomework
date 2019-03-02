package com.helper.controller;

import com.helper.pojo.Admin;
import com.helper.pojo.Teacher;
import com.helper.service.impl.AdminService;
import com.helper.service.impl.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public String login(@RequestParam("id") String id,
                        @RequestParam("password") String password,
                        HttpServletRequest request){
        Admin admin = new Admin();
        admin.setaId(id);admin.setPassword(password);
        Admin result = (Admin) adminService.login(admin);
        if (result!=null){
            request.getSession().setAttribute("adminId",admin.getaId());
            return "redirect:/admin";
        }
        return "redirect:/adminLogin";
    }
}
