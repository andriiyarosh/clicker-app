package com.github.cr9ck.clickerapp.di;

import android.content.Context;

import com.github.cr9ck.clickerapp.App;
import com.github.cr9ck.clickerapp.di.modules.ComponentsModule;
import com.github.cr9ck.clickerapp.di.modules.ModelModule;
import com.github.cr9ck.clickerapp.di.modules.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {ViewModelModule.class, ModelModule.class, ComponentsModule.class, AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance Context context);
    }
}
