/*
Created by Helm  13/01/2017.
*/


package net.andapps.meat4job.data.repositories;

public interface InstagramDataRepository {

    interface GetPictureCallback extends DefaultCallback<String>{};
    void getPicture(GetPictureCallback callback, String clientId);
}
