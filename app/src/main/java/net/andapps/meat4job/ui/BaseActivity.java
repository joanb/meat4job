/*
Created by Helm  11/01/2017.
*/


package net.andapps.meat4job.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import net.andapps.meat4job.Meat4Job;
import net.andapps.meat4job.ui.di.ApplicationComponent;

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDependencyInjection();
        initializeViewsInjection();
    }

    protected ApplicationComponent getApplicationComponent(Meat4Job application) {
        return application.getApplicationComponent();
    }

    protected abstract void initializeDependencyInjection();

    private void initializeViewsInjection() {
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);
        onViewLoaded();
    }

    protected abstract void onViewLoaded();


    protected void showSnackbarWithText(String text, View view) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
    }

//    protected void hideKeyboard() {
//        View view = this.getCurrentFocus();
//        if (view != null) {
//            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
//    }
}