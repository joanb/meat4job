/*
Created by Helm  11/01/2017.
*/


package net.andapps.meat4job;

import android.app.Application;
import net.andapps.meat4job.ui.di.ApplicationComponent;
import net.andapps.meat4job.ui.di.ApplicationModule;
import net.andapps.meat4job.ui.di.DaggerApplicationComponent;

public class Meat4Job extends Application {


    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjection();
    }

    private void initializeInjection() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
