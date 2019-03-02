package com.helper.service.impl;

import com.helper.ConstantBean;
import com.helper.mapper.HomeworkMapper;
import com.helper.mapper.StatusMapper;
import com.helper.mapper.StudentMapper;
import com.helper.mapper.TeacherMapper;
import com.helper.pojo.*;
import com.helper.service.Login;
import com.helper.service.TeacherWork;
import com.helper.util.FileUtil;
import org.apache.ibatis.session.RowBounds;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@Service
@Transactional
public class TeacherService implements Login, TeacherWork {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private StatusMapper statusMapper;
    @Autowired
    private HomeworkMapper homeworkMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ConstantBean constantBean;



    @Override
    public Object login(Object object) {
        try {
            Teacher teacher = (Teacher) object;
            Teacher teaFromSQL = teacherMapper.selectByPrimaryKey(teacher.gettId());
            if(teaFromSQL!=null&&teacher.gettPassword().equals(teaFromSQL.gettPassword())){
                return teaFromSQL;
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Homework postHomework(Homework homework) throws SQLException, IOException {
        /**
         * 先说下流程
         * 先在数据库里面生成作业数据
         * 然后在总作业文件夹中新生成一个文件夹存放当前作业的文件
         * 文件夹命名为：作业名_时间_teacherId
         */
        String folderName = generateFilePath(homework);
        homework.sethFolderName(folderName);
        // 插入数据并返回主键
        homeworkMapper.insertAndReturnKey(homework);
        Long hId = homework.gethId();
        if (hId != null){
            String cId = homework.getcId();
            List<String> sIds = studentMapper.selectSidByCid(cId);
            Status homeworkStatus = newStatus();
            // 生成所有学生的作业状态
            sIds.stream().forEach(sId -> insertStudentHomework(homeworkStatus, sId, cId, hId));
            // 返回刚刚插入的对象
            return homeworkMapper.selectByPrimaryKey(homework.gethId());
        }
        return null;
    }

    /**
     * 创建文件夹并返回文件夹名
     * 命名形式：作业名_时间_teacherId
     * @param homework
     * @return
     */
    public String generateFilePath(Homework homework) throws IOException {
        // 存放作业的总路径
        String basePath = constantBean.static_file_path;
        String time = constantBean.simpleDateFormat.format(new Date(homework.gethEndTime()));
        String folderName =  homework.getcId() + "_" + homework.gethDesc() + "_" + time + "_" + homework.gettId();
        String folederPath = basePath + folderName;
        FileUtil.createFolder(folederPath);
        return folderName;
    }

    @Override
    public Map<String, Integer> checkMembers(Long hId) throws SQLException {
        Homework homework = homeworkFromDBByHid(hId);
        // 班级总人数
        Integer sumPerson = sumClassPerson(homework.getcId());
        // 上交作业的人数
        Integer finishSum = finishHomeworkSum(hId);
        // 未上交作业的人数
        Integer unfinishSum = unFinishHomeworkSum(hId);
        Map<String, Integer> result = new HashMap<>();
        result.put("sumPerson", sumPerson);
        result.put("finishSum", finishSum);
        result.put("unFinishSum", unfinishSum);
        return result;
    }

    @Override
    public Page<HomeworkStatistics> homeworkStatistics(String teacherId, int currentPage, int nums) throws SQLException {
        int offset = currentPage>0 ? (currentPage-1) * nums : 0;
        RowBounds rowBounds = new RowBounds(offset, nums);
        List<Homework> homeworkList = homeworkMapper.homework(teacherId, rowBounds);
        List<HomeworkStatistics>  homeworkStatisticsList = new LinkedList<>();
        HomeworkStatistics  homeworkStatistics = null;
        for (Homework homework : homeworkList) {
            Map<String, Integer> numInfo = checkMembers(homework.gethId());
            homeworkStatistics = new HomeworkStatistics(homework, numInfo.get("sumPerson"),
                    numInfo.get("unFinishSum"), numInfo.get("finishSum"));
            if (homeworkStatistics!=null){
                homeworkStatisticsList.add(homeworkStatistics);
            }
        }
        int totalNums = homeworkMapper.countHomework(teacherId);
        int temp =  (totalNums%nums);
        int totalPage =  (temp==0 ? totalNums/nums:totalNums/nums+1);
        Page<HomeworkStatistics> page = new Page<>(currentPage, totalPage, totalPage, homeworkStatisticsList);
        return page;
    }

    private Status newStatus(){
        return new Status();
    }

    private void insertStudentHomework(Status homeworkStatus, String sId, String cId, Long hId){
        homeworkStatus.setcId(cId);homeworkStatus.sethId(hId);
        homeworkStatus.setsId(sId);homeworkStatus.setsFinish((byte) 0);
        statusMapper.insert(homeworkStatus);
    }

    private Homework homeworkFromDBByHid(Long hId){
        return homeworkMapper.selectByPrimaryKey(hId);
    }

    private Integer sumClassPerson(String cId) throws SQLException {
        return studentMapper.countSumStudentByCid(cId);
    }

    private Integer finishHomeworkSum(Long hId) throws SQLException {
        return statusMapper.finishNum(hId);
    }

    private Integer unFinishHomeworkSum(Long hId) throws SQLException {
        return statusMapper.unFinishNum(hId);
    }
}
