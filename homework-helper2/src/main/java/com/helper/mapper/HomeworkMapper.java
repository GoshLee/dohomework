package com.helper.mapper;

import com.helper.pojo.Homework;
import com.helper.util.MyMapper;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;
import java.util.List;

public interface HomeworkMapper extends MyMapper<Homework> {

    public Long insertAndReturnKey (Homework homework) throws SQLException;

    public List<Homework> myHomeworks (String cId) throws SQLException;

    public Long countHomeworkByCId(String cId) throws SQLException;

    public List<Homework> homework(String  teacherId, RowBounds rowBounds) throws SQLException;

    public Integer countHomework(String teacerId) throws SQLException;

}