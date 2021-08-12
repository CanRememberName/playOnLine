package com.dhkj.playonline.dao;

import com.dhkj.playonline.pojo.File;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDAO {

    //获得所有的文件
    List<File> getAllFile();

    //增加一个文件
    int insertFile(File file);

    //根据别名查找文件
    File searchFileByAnotherName(String another);

    //删除数据库中的所有文件（使用在扫描之前）
    int deleteAllFileBeforeSearch();

    //根据名字查找文件，，模糊查询
    List<File> selectFileByName(String name);
}
