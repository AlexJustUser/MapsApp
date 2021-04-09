package com.example.testapp;

import android.view.View;

public class Presenter {

    private Model model;
    private View view;

    public Presenter(View view) {
        this.model = new Model();
        this.view = view;
    }

    public interface View{

        void updateUserInfoTextView(String info);
        void showProgressBar();
        void hideProgressBar();

    }

}
