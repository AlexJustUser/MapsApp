package com.example.testapp.login.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.testapp.manager.api.ApiManager;
import com.example.testapp.R;
import com.example.testapp.manager.api.model.LoginResponse;
import com.example.testapp.login.ui.LoginView;
import com.example.testapp.sharedpreferences.SharedPreferencesManager;

public class LoginPresenter implements ILoginPresenter {
    private ApiManager apiManager;
    private LoginView view;
    private Context context;
    private SharedPreferencesManager sharedPreferencesManager;

    public LoginPresenter(LoginView view, Context context) {
        this.apiManager = new ApiManager();
        this.view = view;
        this.context = context;
        sharedPreferencesManager = new SharedPreferencesManager(context);
    }

    @Override
    public void checkAccess(String login, String password){
        apiManager.sendAccessData(login, password, this);
    }

    @Override
    public void giveAccess(LoginResponse loginResponse){
        view.setProgressBarVisible(false);
        if(loginResponse.getStatus().equals(context.getString(R.string.ok))){
            sharedPreferencesManager.setSharePrefAttr(context.getString(R.string.code), loginResponse.getCode());
            view.giveAccess();
        }
        else{
            view.updateUserInfoTextView(context.getString(R.string.error));
        }
    }
}