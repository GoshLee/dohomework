package com.helper.service.impl;

import com.helper.mapper.AdminMapper;
import com.helper.pojo.Admin;
import com.helper.service.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Transactional
public class AdminService implements Login {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Object login(Object object) {
        Admin admin = (Admin) object;

        Admin adminFromSQL = adminMapper.selectByPrimaryKey(admin.getaId());
        if (null != adminFromSQL && admin.getaId().equals(adminFromSQL.getaId())
        && admin.getPassword().equals(adminFromSQL.getPassword())){
            return adminFromSQL;
        }
        return null;
    }
}
