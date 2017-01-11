/*
Created by Helm  11/01/2017.
*/


package net.andapps.meat4job.data.repositories;

public interface DefaultCallback<T> {

    void onSuccess(T returnParam);
    void onError(Exception e);

}
