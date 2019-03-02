package com.helper.interceptor;



import com.helper.pojo.JSONResult;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * 拦截器
 * @author SouthLight
 */
public class HomeworkInterceptor implements HandlerInterceptor {

    private final static String IP = "localhost";
    private final static String STU_URL = "/stu";
    private final static String ADMIN_URL = "/admin";
    private final static String TEACH_URL = "/teacher";
    private final static String URL = "/";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter  out = null;

        // 请求路径
        String url = (String) request.getAttribute("org.springframework.web.servlet.HandlerMapping.bestMatchingPattern");
        HttpSession session = request.getSession(false);

        if (session == null){
            response.setStatus(403);
            out = response.getWriter();
            out.append(JSONResult.build(403, "You have to login!", null).toString());
            return false;
        }

        // 通过请求的URL来判断验证的过程
        //如果是学生请求
        if (url.contains(STU_URL)){
            if (null==session.getAttribute("stuId")||"".equals(session.getAttribute("stuId"))){
                response.setStatus(4011);
                out = response.getWriter();
                out.append(JSONResult.build(4011, "You have to login!", null).toString());
                return false;
            }
        }

        // 如果是管理员请求
        else if (url.contains(ADMIN_URL)){
            if (null == session.getAttribute("adminId")){
                response.setStatus(4013);

                out = response.getWriter();
                out.append(JSONResult.build(4013, "You have to login!", null).toString());
                return false;
            }
        }

        // 如果是教师请求
        else if (url.contains(TEACH_URL)){
            if (null == session.getAttribute("teacherId")){
                response.setStatus(4012);
                out = response.getWriter();
                out.append(JSONResult.build(4012, "You have to login!", null).toString());
                return false;
            }
        }

        return true;
    }
}
