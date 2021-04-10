package com.example.testapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.testapp.Presenter.Presenter;
import com.example.testapp.R;

public class EnterActivity extends AppCompatActivity implements Presenter.View{

    private ProgressBar progressBar;
    private Button okButton;
    private EditText login;
    private EditText password;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        okButton = findViewById(R.id.okButton);
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
    }

    public void okAccess(View view){
        initProgressBar();
        presenter = new Presenter(this);
        presenter.checkAccess(login.getText().toString(), password.getText().toString());
    }

    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleSmall);
        progressBar.setIndeterminate(true);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(Resources.getSystem().getDisplayMetrics().widthPixels,250);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        this.addContentView(progressBar, params);
        showProgressBar();
        hideOkButton();
    }

    @Override
    public void updateUserInfoTextView(String info) {
        Toast toast = Toast.makeText(getApplicationContext(),info, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
        showOkButton();
    }

    public void showOkButton(){
        okButton.setEnabled(true);
    }

    public void hideOkButton(){
        okButton.setEnabled(false);
    }
}