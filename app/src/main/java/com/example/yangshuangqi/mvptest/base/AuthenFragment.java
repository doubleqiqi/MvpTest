package com.example.yangshuangqi.mvptest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.yangshuangqi.mvptest.Navigator;
import com.example.yangshuangqi.mvptest.R;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateFragment;

/**
 * Created by yangshuangqi on 16/7/7.
 */
public abstract class AuthenFragment<AV extends View, M, V extends AuthenView<M>, P extends MvpPresenter<V>>
        extends MvpLceViewStateFragment<AV, M, V, P> implements AuthenView<M> {

    protected View authView;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authView = view.findViewById(R.id.authView);
        authView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onAuthenViewClicked();
            }
        });
    }

    private void onAuthenViewClicked() {
        Navigator.showAuthenActivity(getActivity());
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        if (pullToRefresh) {
            return "ops 有错误了";
        } else {
            return "发生错误了，点击进行重试";
        }

    }

    @Override
    public void showAuthenRequired() {

        AuthenViewState vs = (AuthenViewState) viewState;
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        contentView.setVisibility(View.GONE);
        authView.setVisibility(View.VISIBLE);

    }

    @Override
    public void showContent() {
        super.showContent();
        authView.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        authView.setVisibility(View.GONE);


    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        authView.setVisibility(View.GONE);

    }
}
