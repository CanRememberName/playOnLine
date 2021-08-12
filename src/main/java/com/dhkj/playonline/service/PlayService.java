package com.dhkj.playonline.service;

import com.dhkj.playonline.pojo.PlayTime;

public interface PlayService {
    int insertPlayTime(PlayTime playTime);

    int updatePlayTime(String filename, String userIp);

    PlayTime slectPlayTimeByFileName(String filename, String userIp);
}
