/*
Created by Helm  11/01/2017.
*/


package net.andapps.meat4job.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import net.andapps.meat4job.Meat4Job;
import net.andapps.meat4job.ui.di.ApplicationComponent;

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDependencyInjection();
    }

    public ApplicationComponent getApplicationComponent(Meat4Job application) {
        return application.getApplicationComponent();
    }

    protected abstract void initializeDependencyInjection();

}