package com.github.cr9ck.clickerapp.presentation.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.github.cr9ck.clickerapp.R;
import com.github.cr9ck.clickerapp.presentation.viewmodel.LauncherViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    private LauncherViewModel viewModel;
    private ViewModelProvider.Factory factory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        viewModel.launcherViewState.observe(this, mainViewState -> {
            if (mainViewState == ViewState.DEFAULT) {
                Navigation.findNavController(this, R.id.hostFragment).navigate(R.id.action_to_launcher);
            }
        });
    }
}
