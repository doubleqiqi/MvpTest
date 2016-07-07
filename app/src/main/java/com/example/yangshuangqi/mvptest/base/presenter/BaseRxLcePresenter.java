package com.example.yangshuangqi.mvptest.base.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yangshuangqi on 16/7/7.
 */
public abstract class BaseRxLcePresenter<V extends MvpLceView<M>, M>
        extends MvpBasePresenter<V>
        implements MvpPresenter<V> {

    protected Subscriber<M> subscriber;

    protected void unSubscribe() {
        if (subscriber != null && !subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
        }
        subscriber = null;
    }

    public void subscribe(Observable<M> observable, final boolean pullToRefresh) {
        if (isViewAttached()) {
            getView().showLoading(pullToRefresh);
        }
        unSubscribe();
        subscriber = new Subscriber<M>() {
            @Override
            public void onCompleted() {
                BaseRxLcePresenter.this.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                BaseRxLcePresenter.this.onError(e,pullToRefresh);
            }

            @Override
            public void onNext(M m) {

                BaseRxLcePresenter.this.onNext(m);
            }
        };

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

        
    }

    protected void onCompleted() {
        if (isViewAttached()) {
            getView().showContent();
        }
        unSubscribe();
    }

    protected void onError(Throwable throwable, boolean pullToRefresh) {
        if (isViewAttached()) {
            getView().showError(throwable,pullToRefresh);
        }
        unSubscribe();
    }

    protected void onNext(M data) {
        if (isViewAttached()) {
            getView().setData(data);
        }
        unSubscribe();
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance) {
            unSubscribe();
        }
    }
}
