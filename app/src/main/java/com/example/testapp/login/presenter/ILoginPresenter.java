package com.example.testapp.login.presenter;

import com.example.testapp.api.model.LoginResponse;

public interface ILoginPresenter {

    void checkAccess(String login, String password);
    void giveAccess(LoginResponse loginResponse);

}
