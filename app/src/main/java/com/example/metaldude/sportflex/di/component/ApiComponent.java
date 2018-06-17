package com.example.metaldude.sportflex.di.component;

import com.example.metaldude.sportflex.di.modules.ApiModule;
import com.example.metaldude.sportflex.di.modules.AppModule;
import com.example.metaldude.sportflex.views.activities.MainActivity;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface ApiComponent {

    void inject(MainActivity activity);

}
