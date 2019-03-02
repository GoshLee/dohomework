package com.helper.mapper;

import com.helper.pojo.Student;
import com.helper.util.MyMapper;

import java.sql.SQLException;
import java.util.List;

public interface StudentMapper extends MyMapper<Student> {
    public List<String> selectSidByCid(String cId) throws SQLException;

    public Integer countSumStudentByCid(String cId) throws SQLException;


}