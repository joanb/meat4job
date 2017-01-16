package net.andapps.meat4job.ui.menu;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import net.andapps.meat4job.Meat4Job;
import net.andapps.meat4job.R;
import net.andapps.meat4job.ui.BaseActivity;
import net.andapps.meat4job.ui.di.ActivityModule;
import net.andapps.meat4job.ui.di.DaggerActivityComponent;
import net.andapps.meat4job.ui.di.ViewModule;

import javax.inject.Inject;
import java.util.Locale;

public class MenuActivity extends BaseActivity implements MenuView, BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject
    MenuPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView navigationBar;

    public static final String EMPTY = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ButterKnife.bind(this);

        navigationBar.setOnNavigationItemSelectedListener(this);

        toolbar.setTitle(EMPTY);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void initializeDependencyInjection() {
        Meat4Job application = (Meat4Job) getApplication();
        DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent(application))
                .activityModule(new ActivityModule(this))
                .viewModule(new ViewModule(this))
                .build()
                .inject(this);
    }


    private void showLanguageDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
        builder.setTitle(R.string.languaje_pick);

        builder.setPositiveButton(R.string.spanish, new DialogInterface.OnClickListener() {
            String SPANISH = "es";

            public void onClick(DialogInterface dialog, int id) {
                presenter.onChangeLanguage(SPANISH);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.english, new DialogInterface.OnClickListener() {
            String ENGLISH = "en";

            public void onClick(DialogInterface dialog, int id) {
                presenter.onChangeLanguage(ENGLISH);
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void switchLanguageTo(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        Intent refresh = new Intent(MenuActivity.this, MenuActivity.class);
        startActivity(refresh);
        finish();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_tweets:
                presenter.onTweetsSelected();
                break;
            case R.id.action_map:
                presenter.onLocationSelected();
                break;
            case R.id.action_contact:
                presenter.onContactSelected();
                break;
        }
        return true;
    }

    @Override
    public void startFragment(Fragment fragmentToShow) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragmentToShow)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        showLanguageDialog();
        return super.onOptionsItemSelected(item);
    }
}
