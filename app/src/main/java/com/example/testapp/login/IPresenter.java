package com.example.testapp.login;

public interface IPresenter {

    void checkAccess(String login, String password);
    void giveAccess(LoginResponse loginResponse);

}
