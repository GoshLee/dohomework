package com.helper.service;

import com.helper.pojo.Homework;
import com.helper.pojo.HomeworkStatistics;
import com.helper.pojo.Page;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public interface TeacherWork {

    public Homework postHomework (Homework homework) throws SQLException, IOException;

    public Map<String, Integer> checkMembers (Long hId) throws SQLException;

    public Page<HomeworkStatistics> homeworkStatistics(String teacherId, int currentPage, int nums) throws SQLException;

}
