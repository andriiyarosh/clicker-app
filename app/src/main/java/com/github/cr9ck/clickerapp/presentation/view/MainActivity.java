package com.github.cr9ck.clickerapp.presentation.view;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.github.cr9ck.clickerapp.R;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
