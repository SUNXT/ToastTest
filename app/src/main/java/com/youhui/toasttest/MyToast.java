package com.youhui.toasttest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.annotation.Target;

/**
 * Created by SUN on 2017/5/12.
 */

public class MyToast {
    private Toast toast;
    public MyToast(Context context, String text, int duration){
        toast = new Toast(context);
        View toastView = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        TextView textView = (TextView) toastView.findViewById(R.id.text);
        textView.setText(text);
        toast.setDuration(duration);
        toast.setView(toastView);
    }
    public void setGravity(int gravity, int x, int y){
        toast.setGravity(gravity, x , y);
    }

    public void show(){
        toast.show();
    }
}
