package com.example.testapp.Presenter;

import com.example.testapp.AccessStatus;
import com.example.testapp.Model.Model;

public class Presenter {

    private Model model;
    private View view;

    public Presenter(View view) {
        this.model = new Model();
        this.view = view;
    }

    public void checkAccess(String login, String password){
        model.sendAccessData(login, password, this);
    }

    public void giveAccess(AccessStatus accessStatus){
        if(accessStatus.getStatus().equals("ok")){
            view.hideProgressBar();
            view.updateUserInfoTextView("Success");
        }
        else{
            view.hideProgressBar();
            view.updateUserInfoTextView("Error");
        }
    }

    public interface View{
        void updateUserInfoTextView(String info);
        void showProgressBar();
        void hideProgressBar();
    }

}
