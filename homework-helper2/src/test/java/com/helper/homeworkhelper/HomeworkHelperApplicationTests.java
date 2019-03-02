package com.helper.homeworkhelper;

import com.helper.mapper.HomeworkMapper;
import com.helper.mapper.StatusMapper;
import com.helper.mapper.StudentMapper;
import com.helper.mapper.TeacherMapper;
import com.helper.pojo.Homework;
import com.helper.pojo.HomeworkAndStatus;
import com.helper.pojo.Student;
import com.helper.service.impl.TeacherService;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeworkHelperApplicationTests {

    @Autowired
    public StudentMapper studentMapper;
    @Autowired
    public TeacherService teacherService;
    @Autowired
    public TeacherMapper teacherMapper;
    @Autowired
    public StatusMapper statusMapper;
    @Autowired
    public HomeworkMapper homeworkMapper;

    @Test
    public void testHello(){
        System.out.println("Hello!");
    }

    /**
     * 根据班级查询学生学号
     */
    /*@Test
    public void contextLoads() {
        List<String> lists = null;
        try {
            lists = studentMapper.selectSidByCid("1615431");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(lists.toString());
    }

    @Test
    public void teatPostHomework(){
        Homework homework = new Homework();
        homework.setcId("1615431");homework.settId("linnanwei");
        homework.sethDesc("算法作业");
        try {
            teacherService.postHomework(homework);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert homework!= null;
    }

    @Test
    public void teatCountSumStu(){
        try {
            Integer result = studentMapper.countSumStudentByCid("1615431");
            System.out.println(result);
            assert result>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testMyHomeworks(){
        try {
            List<Homework> lists = homeworkMapper.myHomeworks("1615431");
            lists.stream().forEach(homework -> System.out.println(homework.toString()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStuFinishHomework(){
        try {
            System.out.println(statusMapper.stuFinishHomework((long) 8, "161543133"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRowbouds(){
        try {
            List<HomeworkAndStatus> lists = statusMapper.myHomeworkAndStatus("161543133", new RowBounds(0, 2));
            List<HomeworkAndStatus> lists1 = statusMapper.myHomeworkAndStatus("161543133", new RowBounds(1, 2));

            System.out.println(lists.size());
            System.out.println(lists);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/


}
