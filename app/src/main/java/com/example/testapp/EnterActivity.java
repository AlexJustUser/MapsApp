package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class EnterActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

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
    }

    //@Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    //@Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

}