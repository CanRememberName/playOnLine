package com.dhkj.playonline.service;

import com.dhkj.playonline.dao.FileDAO;
import com.dhkj.playonline.pojo.File;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FileServiceImpl implements FileService{

    @Autowired
    private FileDAO fileDAO;

    @Override
    public List<File> getAllFile() {
        return fileDAO.getAllFile();
    }

    @Override
    public int insertFile(File file) {
        return fileDAO.insertFile(file);
    }

    @Override
    public File searchFileByAnotherName(String name) {
        return fileDAO.searchFileByAnotherName(name);
    }

    @Override
    public int deleteAllFileBeforeSearch() {
        return fileDAO.deleteAllFileBeforeSearch();
    }

    @Override
    public List<File> selectFileByName(String name) {
        return fileDAO.selectFileByName(name);
    }
}
