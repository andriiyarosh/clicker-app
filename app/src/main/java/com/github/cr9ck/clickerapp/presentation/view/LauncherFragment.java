package com.github.cr9ck.clickerapp.presentation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.github.cr9ck.clickerapp.R;
import com.github.cr9ck.clickerapp.presentation.viewmodel.LauncherViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatDialogFragment;

public class LauncherFragment extends DaggerAppCompatDialogFragment {

    private LauncherViewModel viewModel;
    private ViewModelProvider.Factory factory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_launcher, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getViewModelStore(), factory).get(LauncherViewModel.class);
        initObservers();
    }

    @Inject
    public void setFactory(ViewModelProvider.Factory factory) {
        this.factory = factory;
    }

    private void initObservers() {
        viewModel.launcherViewState.observe(getViewLifecycleOwner(), state -> {
            switch (state) {
                case WEB:
                    NavHostFragment.findNavController(this).navigate(R.id.action_to_web_page);
                    break;
                case GAME:
                    NavHostFragment.findNavController(this).navigate(R.id.action_to_game);
                    break;
                case ERROR:
                    break;
            }
        });
    }
}
