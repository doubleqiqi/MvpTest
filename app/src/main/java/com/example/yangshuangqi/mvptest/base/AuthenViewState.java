package com.example.yangshuangqi.mvptest.base;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;

/**
 * Created by yangshuangqi on 16/7/7.
 */
public interface AuthenViewState<D,V extends AuthenView<D>>extends LceViewState<D,V> {
    int SHOWING_AUTHENTICATION_REQUIRED=2;
    void setShowingAuthenticationRequired();
}
