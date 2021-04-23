package com.example.testapp.login.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.testapp.api.ApiManager;
import com.example.testapp.R;
import com.example.testapp.api.model.LoginResponse;
import com.example.testapp.login.ui.LoginView;

public class LoginPresenter implements ILoginPresenter {

    private ApiManager apiManager;
    private LoginView view;
    private Context context;
    private SharedPreferences prefs;

    public LoginPresenter(LoginView view, Context context) {
        this.apiManager = new ApiManager();
        this.view = view;
        this.context = context;
        prefs = context.getSharedPreferences(context.getString(R.string.package_name), context.MODE_PRIVATE);
    }

    @Override
    public void checkAccess(String login, String password){
        apiManager.sendAccessData(login, password, this);
    }

    @Override
    public void giveAccess(LoginResponse loginResponse){
        view.setProgressBarVisible(false);
        if(loginResponse.getStatus().equals(context.getString(R.string.ok))){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(context.getString(R.string.code), loginResponse.getCode());
            editor.commit();
            view.giveAccess();
        }
        else{
            view.updateUserInfoTextView(context.getString(R.string.error));
        }
    }
}