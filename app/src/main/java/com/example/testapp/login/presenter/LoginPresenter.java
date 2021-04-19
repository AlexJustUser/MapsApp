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
        prefs = context.getSharedPreferences("com.example.testapp", context.MODE_PRIVATE);
    }

    public void checkAccess(String login, String password){
        apiManager.sendAccessData(login, password, this);
    }

    public void giveAccess(LoginResponse loginResponse){
        view.setProgressBarVisible(false);
        if(loginResponse.getStatus().equals("ok")){
            view.updateUserInfoTextView(context.getString(R.string.success));
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("code", loginResponse.getCode());
            editor.commit();
        }
        else{
            view.updateUserInfoTextView(context.getString(R.string.error));
        }
    }
}
