package com.dhkj.playonline.service;

import com.dhkj.playonline.dao.PlayDAO;
import com.dhkj.playonline.pojo.PlayTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PlayServiceImpl implements PlayService{

    @Autowired
    private PlayDAO playDAO;

    @Override
    public int insertPlayTime(PlayTime playTime) {
        return playDAO.insertPlayTime(playTime);
    }

    @Override
    public int updatePlayTime(String filename, String userIp) {
        return playDAO.updatePlayTime(filename, userIp);
    }

    @Override
    public PlayTime slectPlayTimeByFileName(String filename, String userIp) {
        return playDAO.slectPlayTimeByFileName(filename, userIp);
    }


}
