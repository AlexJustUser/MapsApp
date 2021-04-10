package com.example.testapp.Model;


import android.util.Log;

import com.example.testapp.AccessStatus;

import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Model {

    private String status;
    private Subscription subscription;
    private static final String TAG = Model.class.getSimpleName();
    public String sendAccessData(String login, String password) {

        subscription = ApiWorker.getApiService()
                .checkAuth(login, password)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<AccessStatus>>() {
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
                    public void onNext(List<AccessStatus> listStatus) {
                        Log.d(TAG, "In onNext()");
                        System.out.println(listStatus.get(0));
                        status = listStatus.get(0).toString();
                    }
                });

            return "";
    }



}
