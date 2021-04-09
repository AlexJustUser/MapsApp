package com.example.testapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.testapp.R;

public class EnterActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        okButton = findViewById(R.id.okButton);
        initProgressBar();

    }

    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleSmall);
        progressBar.setIndeterminate(true);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(Resources.getSystem().getDisplayMetrics().widthPixels,
                250);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        this.addContentView(progressBar, params);
        showProgressBar();
        hideOkButton();
    }

    //@Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void showOkButton(){
        okButton.setEnabled(true);
    }

    public void hideOkButton(){
        okButton.setEnabled(false);
    }

    //@Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

}