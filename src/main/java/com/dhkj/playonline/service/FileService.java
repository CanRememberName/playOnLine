package com.dhkj.playonline.service;

import com.dhkj.playonline.pojo.File;

import java.util.List;

public interface FileService {

    //获取所有文件
    List<File> getAllFile();

    //新增文件
    int insertFile(File file);

    //根据别名查找文件
    File searchFileByAnotherName(String name);

    //删除表中所有数据（在扫描前）
    int deleteAllFileBeforeSearch();

    //根据名字查找文件，模糊查询
    List<File> selectFileByName(String name);

}
