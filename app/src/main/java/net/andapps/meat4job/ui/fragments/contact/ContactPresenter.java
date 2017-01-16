/*
Created by Helm  14/01/2017.
*/


package net.andapps.meat4job.ui.fragments.contact;

import net.andapps.meat4job.data.repositories.InstagramDataRepository;

import javax.inject.Inject;

public class ContactPresenter {

    private static final String TOKEN_URL = "https://api.instagram.com/oauth/authorize/?client_id=3dcd93a2418e4861a44c01342a78bc74&redirect_uri=http://github.com/joanb/meat4job/&response_type=token";
    private final ContactView view;
    private final InstagramDataRepository repository;

    @Inject
    public ContactPresenter(ContactView view, InstagramDataRepository repository) {
        this.view = view;
        this.repository = repository;
    }


    void onStart() {
        view.initializeWebView(TOKEN_URL);
    }

    void onPageFinished(String url) {
        String ACCESS_TOKEN = "access_token";
        if (url.contains(ACCESS_TOKEN)) {
            String accessToken = url.split("=")[1];
            repository.getPicture(new InstagramDataRepository.GetPictureCallback() {
                @Override
                public void onSuccess(String returnParam) {
                    view.showPicture(returnParam);
                }

                @Override
                public void onError(Exception e) {

                }
            }, accessToken);
        }
    }


}