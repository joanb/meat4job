package net.andapps.meat4job.ui.menu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import butterknife.BindView;
import net.andapps.meat4job.Meat4Job;
import net.andapps.meat4job.R;
import net.andapps.meat4job.ui.BaseActivity;
import net.andapps.meat4job.ui.di.ActivityModule;
import net.andapps.meat4job.ui.di.DaggerActivityComponent;
import net.andapps.meat4job.ui.di.ViewModule;

import javax.inject.Inject;

public class MenuActivity extends BaseActivity implements MenuView, BottomNavigationView.OnNavigationItemSelectedListener{

    @Inject MenuPresenter presenter;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView navigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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

    @Override
    protected void onViewLoaded() {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_info:
                presenter.onInfoSleted();
                break;
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
        return true;    }

    @Override
    public void startFragment(Fragment fragmentToShow) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragmentToShow)
                .commit();
    }
}
