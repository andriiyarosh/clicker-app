package com.github.cr9ck.clickerapp.di.modules;

import com.github.cr9ck.clickerapp.presentation.view.game.GameActivity;
import com.github.cr9ck.clickerapp.presentation.view.game.GameFragment;
import com.github.cr9ck.clickerapp.presentation.view.launcher.LauncherFragment;
import com.github.cr9ck.clickerapp.presentation.view.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ComponentsModule {

    @ContributesAndroidInjector
    abstract LauncherFragment contributeLauncherFragment();

    @ContributesAndroidInjector
    abstract GameFragment contributeGameFragment();

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector
    abstract GameActivity contributeGameActivity();
}
