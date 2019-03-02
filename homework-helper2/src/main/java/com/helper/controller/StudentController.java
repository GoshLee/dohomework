package com.helper.controller;

import com.helper.pojo.*;
import com.helper.service.FileService;
import com.helper.service.impl.StudentService;
import org.apache.catalina.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("stu")
//@CrossOrigin(origins = {"http://localhost"})
public class StudentController {
    final static Logger log = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentService studentService;


    @GetMapping("/test")
    @ResponseBody
    public JSONResult test(HttpServletRequest request){
        String id =  request.getSession(false).getId();
        String stuId = (String) request.getSession(false).getAttribute("stuId");
        System.out.println("stuId:"+stuId);
        return JSONResult.ok(stuId);
    }


    @PostMapping("login")
    public JSONResult login(@RequestParam("id") String id,
                            @RequestParam("password") String password,
                            HttpServletRequest request,
                            HttpServletResponse response){
        Student student = new Student();
        student.setsId(id);student.setsPassword(password);
        Student result = (Student) studentService.login(student);
        if (result==null){
            return JSONResult.build(404,"Student error", null);
        }
        // 创建session
        request.getSession().setAttribute("stuId", student.getsId());
        String jessionid = request.getSession(false).getId();
        // response.setHeader("jessionid",jessionid);
        System.out.println("stuSessionId：" + jessionid);
        return JSONResult.ok(result.getsId());
    }

    /**
     * 注销登录
     * @param request
     * @return
     */
    @GetMapping("logout")
    public  JSONResult logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        // 注销Session
        if (session!=null){
            session.removeAttribute("stuId");
            session.invalidate();
        }
        return JSONResult.ok();
    }

    @GetMapping("/homework")
    @ResponseBody
    public JSONResult myHomework(HttpServletRequest request,
                                 @RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
                                 @RequestParam(name = "nums", defaultValue = "5") int nums){
        // System.out.println("stuId:"+stuId);
        try {
            String stuId = (String) request.getSession(false).getAttribute("stuId");
            Page<HomeworkAndStatus> result = studentService.getHomeworkAndStatus(stuId, currentPage, nums);
            return JSONResult.ok(result);
        } catch (SQLException e) {
            log.error("myHomework error", e);
        } catch (NullPointerException e){
            log.error("NullPointerException , Session is null ", e);
        }
        return JSONResult.errorMsg("Server error");
    }


    @PostMapping(value = {"/upHomework/{hId}"})
    @ResponseBody
    public JSONResult upFile(@RequestParam("file") MultipartFile file,
                             HttpServletRequest request,
                             @PathVariable Long hId){
        if (file==null){
            return JSONResult.build(400, "File is null.", null);
        }

        try {
            String sId = (String) request.getSession(false).getAttribute("stuId");
            int result = studentService.finishHomework(file, sId, hId);
            return result==1?JSONResult.build(201,"success",null):
                    JSONResult.build(200,"You has finished homework.",null);
        } catch (IOException e) {
            log.error("Student upFile error", e);
            return JSONResult.errorMsg("Server error");
        }catch (SQLException e){
            log.error("Student upFile error", e);
            return JSONResult.errorMsg("Server error");
        }
    }
}
