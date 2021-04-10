package com.example.testapp.Presenter;

import com.example.testapp.Model.Model;

public class Presenter {

    private Model model;
    private View view;

    public Presenter(View view) {
        this.model = new Model();
        this.view = view;
    }

    public void checkAccess(String login, String password){
        model.sendAccessData(login, password);

    }

    public interface View{
        void updateUserInfoTextView(String info);
        void showProgressBar();
        void hideProgressBar();
    }

}
