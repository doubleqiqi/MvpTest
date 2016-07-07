package com.example.yangshuangqi.mvptest;

import android.content.Context;
import android.content.Intent;

/**
 * Created by yangshuangqi on 16/7/7.
 */
public class Navigator {

    public static void showAuthenActivity(Context context) {
        context.startActivity(new Intent(context,LoginActivity.class));
    }
}
