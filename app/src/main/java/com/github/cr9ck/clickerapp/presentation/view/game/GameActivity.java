package com.github.cr9ck.clickerapp.presentation.view.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.github.cr9ck.clickerapp.R;
import com.github.cr9ck.clickerapp.presentation.view.MainActivity;
import com.github.cr9ck.clickerapp.presentation.view.ViewState;
import com.github.cr9ck.clickerapp.presentation.viewmodel.LauncherViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class GameActivity extends DaggerAppCompatActivity {

    private LauncherViewModel viewModel;
    private ViewModelProvider.Factory factory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        viewModel = new ViewModelProvider(this, factory).get(LauncherViewModel.class);
        initObservers();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reset, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.reset) {
            viewModel.resetState();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Inject
    public void setFactory(ViewModelProvider.Factory factory) {
        this.factory = factory;
    }

    private void initObservers() {
        viewModel.launcherViewState.observe(this, viewState -> {
            if (viewState == ViewState.DEFAULT) {
                Intent mainIntent = new Intent(this, MainActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(mainIntent);
                GameActivity.this.finish();
            }
        });
    }
}
