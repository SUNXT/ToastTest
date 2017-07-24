package com.youhui.toasttest;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Created by SUN on 2017/5/12.
 */

public class PopToast extends PopupWindow {

    private int duration;

    public PopToast(Context context, String text, int duration){
        View view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(text);
        setContentView(view);
        setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setFocusable(false);
        setOutsideTouchable(true);
        setAnimationStyle(R.style.dismiss);

        this.duration = duration;
    }

    public void dismiss(){
        super.dismiss();
    }

    public void show(View view, int gravity, int x, int y){
        this.showAtLocation(view, gravity, x, y);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        },
        duration);
    }
}
