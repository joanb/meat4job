package net.andapps.meat4job.ui.menu;

import net.andapps.meat4job.ui.fragments.maps.MapsFragment;

import javax.inject.Inject;

/**
 * Created by joanbarroso on 11/01/2017.
 */

public class MenuPresenter {

    private final MenuView view;

    @Inject
    MenuPresenter(MenuView view) {
        this.view = view;
    }


    void onInfoSleted() {

    }

    void onTweetsSelected() {

    }

    void onLocationSelected() {
        view.startFragment(new MapsFragment());
    }

    void onContactSelected() {

    }
}
