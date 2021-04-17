package com.example.testapp.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.testapp.R;

public class LoginView extends AppCompatActivity implements Presenter.View{

    private ProgressBar login_progressbar;
    private Button login_button;
    private EditText login_text_login;
    private EditText login_text_password;
    private IPresenter iPresenter;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    public void onLoginButtonClick(View view){
        if(login_text_login.getText().toString().equals("")||login_text_password.getText().toString().equals("")){
            updateUserInfoTextView(getResources().getString(R.string.insert));
        }
        else{
            initProgressBar();
            iPresenter.checkAccess(login_text_login.getText().toString(), login_text_password.getText().toString());
        }
    }

    private void initProgressBar() {
        showProgressBar();
        hideOkButton();
    }

    @Override
    public void updateUserInfoTextView(String info) {
        toast = Toast.makeText(getApplicationContext(),info, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void showProgressBar() {
        login_progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        login_progressbar.setVisibility(View.INVISIBLE);
        showOkButton();
    }

    public void showOkButton(){
        login_button.setEnabled(true);
    }

    public void hideOkButton(){
        login_button.setEnabled(false);
    }

    public void init(){
        login_progressbar = findViewById(R.id.login_progressbar);
        login_button = findViewById(R.id.login_button);
        login_text_login = findViewById(R.id.login_text_login);
        login_text_password = findViewById(R.id.login_text_password);
        iPresenter = new Presenter(this, this);
    }
}