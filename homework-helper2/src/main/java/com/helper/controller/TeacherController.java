package com.helper.controller;

import com.helper.pojo.*;
import com.helper.service.impl.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("teacher")
public class TeacherController {
    final static Logger log = LoggerFactory.getLogger(TeacherController.class);
    /** 作业截止日期格式 */
    final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/login")
    @ResponseBody
    public JSONResult login(@RequestParam("id") String id,
                        @RequestParam("password") String password,
                        HttpServletRequest request){
        Teacher teacher = new Teacher();
        teacher.settId(id);teacher.settPassword(password);
        Teacher result = (Teacher) teacherService.login(teacher);
        if (result!=null){
            request.getSession().setAttribute("teacherId",teacher.gettId());
            return JSONResult.ok(teacher.gettId());
        }
        return JSONResult.build(404,"Teacher error", null);
    }


    /**
     * 注销登录
     * @param request
     * @return
     */
    @GetMapping("logout")
    @ResponseBody
    public  JSONResult logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        // 注销Session
        if (session!=null){
            session.removeAttribute("teacherId");
            session.invalidate();
        }
        return JSONResult.ok();
    }


    @PostMapping("/postHomework")
    @ResponseBody
    public JSONResult postHomework(HttpServletRequest request,
                                   String hName,
                                   String cId,
                                   String hTime){
        HttpSession session = request.getSession(false);
        String teacherId = (String) session.getAttribute("teacherId");
        Homework homework = new Homework();
        homework.setcId(cId);homework.sethDesc(hName);homework.settId(teacherId);
        try {
            // 将截止日期转换成时间戳存储到数据库
            homework.sethEndTime(dateFormat.parse(hTime).getTime());
        } catch (ParseException e) {
            // 返回提示日期格式错误
            return JSONResult.build(400,"Date format error", null);
        }
        // 存储发布作业的时间戳
        homework.sethStartTime(System.currentTimeMillis());
        Homework result = null;
        try {
            result = teacherService.postHomework(homework);
            if (result != null){
                return JSONResult.build(201,"success",result);
            }
        } catch (SQLException e) {
            return JSONResult.build(404, "Class Id is error", null);
        } catch (IOException e) {
            log.error("postHomework error,IOException", e);
        } catch (Exception e){
            return JSONResult.build(404, "Class Id is error", null);
        }
        return JSONResult.errorMsg("Server error");
    }

    @GetMapping("/homeworkStatistics")
    @ResponseBody
    public JSONResult homeworkStatistics(HttpServletRequest request,
                                         @RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
                                         @RequestParam(name = "nums", defaultValue = "5") int nums){
        HttpSession session = request.getSession(false);
        String teacherId = (String) session.getAttribute("teacherId");
        if (teacherId==null){
            return null;
        }
        try {
            Page<HomeworkStatistics> page = teacherService.homeworkStatistics(teacherId, currentPage, nums);
            return JSONResult.ok(page);
        } catch (SQLException e) {
            log.error("homeworkStatistics error", e);
        }
        return null;
    }
}
