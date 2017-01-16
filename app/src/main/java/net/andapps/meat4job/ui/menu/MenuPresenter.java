package net.andapps.meat4job.ui.menu;

import net.andapps.meat4job.ui.fragments.contact.ContactFragment;
import net.andapps.meat4job.ui.fragments.maps.MapsFragment;
import net.andapps.meat4job.ui.fragments.tweets.TweetsFragment;

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

    void onTweetsSelected() {
        view.startFragment(new TweetsFragment());
    }

    void onLocationSelected() {
        view.startFragment(new MapsFragment());
    }

    void onContactSelected() {
        view.startFragment(new ContactFragment());
    }

    public void onChangeLanguage(String language) {
        view.switchLanguageTo(language);
    }
}
