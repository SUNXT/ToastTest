package com.youhui.toasttest;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Button btn_start_toast;
    private EditText et_toast_count;
    private EditText et_toast_duration;
    private EditText et_show_toast_time;

    private boolean stop = false;
    private int i = 0;
    private int count = 10;
    private TimerTask timerTask;
    private Timer timer = new Timer();
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (i != count)
            {
                PopToast popToast = new PopToast(MainActivity.this, "toast:" + i, getToastDuration());
                popToast.show(getCurrentFocus(),Gravity.CENTER, 0, 0);
                i ++;
            }else {
                timerTask.cancel();
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        btn_start_toast = (Button) findViewById(R.id.btn_start_toast);
        btn_start_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stop){
                    stop = false;
                    showMeetingUserChange();
//                    stopToast();
                    btn_start_toast.setText("开始显示Toast");
                }else {
                    stop = true;
//                    startToast();
                    showMeetingUserChange();
                    btn_start_toast.setText("关闭显示Toast");
                }
            }
        });

        et_toast_count = (EditText) findViewById(R.id.et_toast_count);
        et_toast_duration = (EditText) findViewById(R.id.et_toast_duration);
        et_show_toast_time = (EditText) findViewById(R.id.et_toast_show_time);
    }

    private void startToast(){
        getToastCount();
        i = 0;
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        };
        if (timer == null){
            timer = new Timer();
        }
        timer.schedule(timerTask, 0, getShowToastTime());
    }

    private void stopToast(){
        timerTask.cancel();
    }

    private void getToastCount(){
        String countStr = et_toast_count.getText().toString().trim();
        try {
            count = Integer.parseInt(countStr);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    private int getShowToastTime(){
        String timeStr = et_show_toast_time.getText().toString().trim();
        int time = 200;
        try {
            time = Integer.parseInt(timeStr);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return time;
    }

    private int getToastDuration(){
        String timeStr = et_toast_duration.getText().toString().trim();
        int time = 200;
        try {
            time = Integer.parseInt(timeStr);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return time;
    }

    private void showMeetingUserChange(){
        final myHandler handler = new myHandler(MainActivity.this);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        };
        timer.schedule(timerTask, 1000, 450);
    }

    public static class myHandler extends Handler{
        private final WeakReference<MainActivity> mActivity;
        public  myHandler(MainActivity activity){
            mActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            PopToast toast = new PopToast(mActivity.get(), "000", 100);
            toast.show(mActivity.get().getCurrentFocus(), Gravity.BOTTOM, 0 ,150);
        }
    }
}
