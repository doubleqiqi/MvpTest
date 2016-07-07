package com.example.yangshuangqi.mvptest.base;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

/**
 * Created by yangshuangqi on 16/7/7.
 */
public interface AuthenView<M> extends MvpLceView<M> {
    void showAuthenRequired();
}
