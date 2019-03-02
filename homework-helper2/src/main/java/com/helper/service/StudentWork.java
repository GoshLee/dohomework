package com.helper.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

public interface StudentWork {

    public int finishHomework(MultipartFile file, String sId, Long hId) throws SQLException, IOException;
}
