package com.example.testapp.login.presenter;

import com.example.testapp.manager.api.model.LoginResponse;

public interface ILoginPresenter {
    void checkAccess(String login, String password);
    void giveAccess(LoginResponse loginResponse);
}
