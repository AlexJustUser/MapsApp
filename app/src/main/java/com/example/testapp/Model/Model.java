package com.example.testapp.Model;


import android.util.Log;
import com.example.testapp.AccessStatus;
import com.example.testapp.Presenter.Presenter;
import java.util.concurrent.TimeUnit;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Model {

    private Subscription subscription;
    private static final String TAG = Model.class.getSimpleName();



    public void sendAccessData(String login, String password, final Presenter presenter) {

        try {
            subscription = ApiWorker.getApiService()
                    .checkAuth(login, password)
                    .debounce(500, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .subscribe(new Subscriber<AccessStatus>() {
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
                        public void onNext(AccessStatus accessStatus) {
                            Log.d(TAG, "In onNext()");
                            presenter.giveAccess(accessStatus);
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
