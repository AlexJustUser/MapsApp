package com.example.testapp.manager.api;

import android.util.Log;

import com.example.testapp.manager.api.model.DataResponse;
import com.example.testapp.manager.api.model.LoginResponse;
import com.example.testapp.login.presenter.ILoginPresenter;
import com.example.testapp.mapslist.presenter.IMapsListPresenter;

import java.util.concurrent.TimeUnit;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ApiManager {
    private Subscription subscription;
    private static final String TAG = ApiManager.class.getSimpleName();

    public void sendAccessData(String login, String password, final ILoginPresenter presenter) {
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

    public void getMapsList(String code, int pageNum, final IMapsListPresenter presenter) {
        try {
            subscription = ApiWorker.getApiService()
                    .getMapsList(code, String.valueOf(pageNum))
                    .debounce(500, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<DataResponse>() {
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
                        public void onNext(DataResponse dataResponse) {
                            Log.d(TAG, "In onNext()");
                            presenter.displayMapsList(dataResponse);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
