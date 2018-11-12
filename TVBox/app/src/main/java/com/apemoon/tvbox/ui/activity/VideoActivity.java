package com.apemoon.tvbox.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.ImageView;

import com.apemoon.tvbox.R;
import com.apemoon.tvbox.base.BaseActivity;
import com.apemoon.tvbox.entity.UserEntity;
import com.apemoon.tvbox.entity.VideoDetailInfo;
import com.apemoon.tvbox.interfaces.ILoginView;
import com.apemoon.tvbox.presenter.SplashPresenter;
import com.apemoon.tvbox.utils.GlobalUtil;
import com.apemoon.tvbox.utils.PreferenceUtil;
import com.boredream.bdvideoplayer.BDVideoView;
import com.boredream.bdvideoplayer.bean.IVideoInfo;
import com.boredream.bdvideoplayer.listener.SimpleOnVideoControlListener;
import com.boredream.bdvideoplayer.utils.DisplayUtils;

import butterknife.BindView;

/**
 * Created by water on 2018/8/31/031.
 * des：启动界面
 */

public class VideoActivity extends BaseActivity {
    public static final String VIDEO_INFO = "video_info";
    private VideoDetailInfo iVideoInfo;

    @BindView(R.id.video_view)
    BDVideoView videoView;

    @Override
    public int getLayoutRes() {
        return R.layout.layout_dialog_fee;
    }

    @Override
    public void initView() {

    }

    /**
     * 视频加载
     */
    @Override
    public void initData() {
        if(getIntent()!=null){
            iVideoInfo = (VideoDetailInfo) getIntent().getExtras().get(VIDEO_INFO);

            videoView.setOnVideoControlListener(new SimpleOnVideoControlListener() {

                @Override
                public void onRetry(int errorStatus) {
                    // TODO: 2017/6/20 调用业务接口重新获取数据
                    // get info and call method "videoView.startPlayVideo(info);"
                    videoView.startPlayVideo(iVideoInfo);
                }

                @Override
                public void onBack() {
                    finish();
                }

                @Override
                public void onFullScreen() {
                    DisplayUtils.toggleScreenOrientation(VideoActivity.this);
                }
            });
            videoView.startPlayVideo(iVideoInfo);
            videoView.requestFocus();

        }
    }

    @Override
    public void initListener() {

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();

        videoView.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();

        videoView.onStart();
    }

}
