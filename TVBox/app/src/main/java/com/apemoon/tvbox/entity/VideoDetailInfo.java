package com.apemoon.tvbox.entity;

import com.boredream.bdvideoplayer.bean.IVideoInfo;

public class VideoDetailInfo implements IVideoInfo {

    public String title;
    public String videoPath;

    public VideoDetailInfo(String title,String videoPath){
        this.title = title;
        this.videoPath = videoPath;
    }

    @Override
    public String getVideoTitle() {
        return title;
    }

    @Override
    public String getVideoPath() {
        return videoPath;
    }
}
