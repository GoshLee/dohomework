package com.helper.mapper;

import com.helper.pojo.HomeworkAndStatus;
import com.helper.pojo.Status;
import com.helper.util.MyMapper;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;
import java.util.List;

public interface StatusMapper extends MyMapper<Status> {



    public Integer stuFinishHomework(Long hId, String sId) throws  SQLException;

    public List<HomeworkAndStatus> myHomeworkAndStatus(String sId, RowBounds rowBounds) throws SQLException;

    /**
     * 查询未完成作业的人数
     * @param hId 作业id
     * @return
     * @throws SQLException
     */
    public Integer  unFinishNum(Long hId) throws SQLException;

    /**
     * 查询完成作业的人数
     * @param hId  作业id
     * @return
     * @throws SQLException
     */
    public Integer finishNum(Long hId) throws SQLException;

}