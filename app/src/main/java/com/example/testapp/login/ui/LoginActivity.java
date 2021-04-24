package com.example.testapp.login.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import com.example.testapp.R;
import com.example.testapp.databinding.ActivityLoginBinding;
import com.example.testapp.login.presenter.ILoginPresenter;
import com.example.testapp.login.presenter.LoginPresenter;
import com.example.testapp.mapslist.ui.MapsListActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private ILoginPresenter presenter;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    public void init() {
        presenter = new LoginPresenter(this, this);
        binding.loginButton.setOnClickListener((v) -> {
            if (binding.loginEdit.getText().toString().equals("") || binding.loginPasswordEdit.getText().toString().equals("")) {
                updateUserInfoTextView(getResources().getString(R.string.insert));
            } else {
                setProgressBarVisible(true);
                presenter.checkAccess(binding.loginEdit.getText().toString(), binding.loginPasswordEdit.getText().toString());
                if (binding.loginCheckBox.isChecked()) {
                    //logic to save login and password if user wants it
                }
            }
        });
    }

    @Override
    public void updateUserInfoTextView(String info) {
        Toast.makeText(getApplicationContext(), info, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setProgressBarVisible(Boolean isVisible) {
        setLoginButtonEnable(!isVisible);
        if (isVisible)
            binding.loginProgressbar.setVisibility(View.VISIBLE);
        else
            binding.loginProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void giveAccess(){
        startActivity(new Intent(LoginActivity.this, MapsListActivity.class));
    }

    public void setLoginButtonEnable(Boolean isEnable) {
        binding.loginButton.setEnabled(isEnable);
    }
}