package com.github.cr9ck.clickerapp.di.modules;

import com.github.cr9ck.clickerapp.presentation.view.LauncherFragment;
import com.github.cr9ck.clickerapp.presentation.view.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ComponentsModule {

    @ContributesAndroidInjector
    abstract LauncherFragment contributeLauncherFragment();

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();
}
