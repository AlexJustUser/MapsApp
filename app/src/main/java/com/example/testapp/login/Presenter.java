package com.example.testapp.login;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.testapp.Api.ApiManager;
import com.example.testapp.R;

public class Presenter implements IPresenter{

    private ApiManager apiManager;
    private View view;
    private Context context;
    private SharedPreferences prefs = null;

    public Presenter(View view, Context context) {
        this.apiManager = new ApiManager();
        this.view = view;
        this.context = context;
        //prefs = getSharedPreferences("com.example.gai_app", MODE_PRIVATE);
    }

    public void checkAccess(String login, String password){
        apiManager.sendAccessData(login, password, this);
    }

    public void giveAccess(LoginResponse loginResponse){
        if(loginResponse.getStatus().equals("ok")){
            view.hideProgressBar();
            view.updateUserInfoTextView(context.getString(R.string.success));
        }
        else{
            view.hideProgressBar();
            view.updateUserInfoTextView(context.getString(R.string.error));
        }
    }

    public interface View{
        void updateUserInfoTextView(String info);
        void showProgressBar();
        void hideProgressBar();
    }

}
