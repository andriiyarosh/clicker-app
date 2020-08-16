package com.github.cr9ck.clickerapp.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.github.cr9ck.clickerapp.di.viewmodel.ViewModelFactory;
import com.github.cr9ck.clickerapp.di.viewmodel.ViewModelKey;
import com.github.cr9ck.clickerapp.presentation.viewmodel.GameViewModel;
import com.github.cr9ck.clickerapp.presentation.viewmodel.LauncherViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LauncherViewModel.class)
    abstract ViewModel bindLauncherViewModel(LauncherViewModel launcherViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(GameViewModel.class)
    abstract ViewModel bindGameViewModel(GameViewModel gameViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
