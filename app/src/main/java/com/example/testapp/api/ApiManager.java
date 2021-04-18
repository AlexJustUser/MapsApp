package com.example.testapp.api;


import android.util.Log;

import com.example.testapp.api.model.LoginResponse;
import com.example.testapp.login.presenter.LoginPresenter;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ApiManager {

    private Subscription subscription;
    private static final String TAG = ApiManager.class.getSimpleName();


    public void sendAccessData(String login, String password, final LoginPresenter presenter) {
        try {
            subscription = ApiWorker.getApiService()
                    .checkAuth(login, password)
                    .debounce(500, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<LoginResponse>() {
                        @Override
                        public void onCompleted() {
                            Log.d(TAG, "In onCompleted()");
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            Log.d(TAG, "In onError()");
                        }

                        @Override
                        public void onNext(LoginResponse loginResponse) {
                            Log.d(TAG, "In onNext()");
                            presenter.giveAccess(loginResponse);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
