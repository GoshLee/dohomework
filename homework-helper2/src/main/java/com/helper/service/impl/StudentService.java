package com.helper.service.impl;

import com.helper.mapper.HomeworkMapper;
import com.helper.mapper.StatusMapper;
import com.helper.mapper.StudentMapper;
import com.helper.pojo.Homework;
import com.helper.pojo.HomeworkAndStatus;
import com.helper.pojo.Page;
import com.helper.pojo.Student;
import com.helper.service.Login;
import com.helper.service.StudentWork;
import com.helper.util.FileUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class StudentService implements Login, StudentWork {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private HomeworkMapper homeworkMapper;
    @Autowired
    private StatusMapper statusMapper;
    @Override
    public Object login(Object object) {
        try {
            Student student = (Student) object;
            Student stuFromSQL = studentMapper.selectByPrimaryKey(student.getsId());
            if(stuFromSQL!=null &&student.getsPassword().equals(stuFromSQL.getsPassword())){
                return stuFromSQL;
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public boolean saveFile(MultipartFile file, String folderName) throws IOException{
        return FileUtil.saveFileToLocal(file,folderName);
    }

    public List<Homework> getHomeworks(String stuId) throws SQLException {
        String cId = getClassId(stuId);
        return homeworkMapper.myHomeworks(cId);
    }

    public Page<HomeworkAndStatus> getHomeworkAndStatus(String stuId, int currentPage, int nums) throws SQLException {
        int offset = currentPage>0 ? (currentPage-1) * nums : 0;
        Student student = studentMapper.selectByPrimaryKey(stuId);
        List<HomeworkAndStatus> list = statusMapper.myHomeworkAndStatus(stuId, new RowBounds(offset, nums));
        Long totalNumber = homeworkMapper.countHomeworkByCId(student.getcId());
        int temp = (int) (totalNumber%nums);
        int totalPage = (int) (temp==0 ? totalNumber/nums:totalNumber/nums+1);
        Page<HomeworkAndStatus> page = new Page<>(currentPage, totalPage, totalNumber, list);
        return page;
    }

    private String getClassId (String stuId){
        return studentMapper.selectByPrimaryKey(stuId).getcId();
    }

    @Override
    public int finishHomework(MultipartFile file, String sId, Long hId) throws SQLException, IOException {
        String folderName = homeworkMapper.selectByPrimaryKey(hId).gethFolderName();
        // 存储文件到文件夹
        saveFile(file, folderName);
        return statusMapper.stuFinishHomework(hId,sId);
    }
}
