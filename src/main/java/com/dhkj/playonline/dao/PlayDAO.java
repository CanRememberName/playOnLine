package com.dhkj.playonline.dao;

import com.dhkj.playonline.pojo.PlayTime;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayDAO {
    int insertPlayTime(PlayTime playTime);

    int updatePlayTime(String filename, String userIp);

    PlayTime slectPlayTimeByFileName(String filename, String userIp);
}
