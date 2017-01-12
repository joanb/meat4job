package net.andapps.meat4job.ui.menu;

import android.support.v4.app.Fragment;

/**
 * Created by joanbarroso on 11/01/2017.
 */

public interface MenuView {
    void startFragment(Fragment fragmentToShow);

    void initializeViews();
}
