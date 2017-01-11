/*
Created by Helm  11/01/2017.
*/


package net.andapps.meat4job.data.repositories;

import net.andapps.meat4job.data.model.PlaceLocations;

public interface MapsDataRepository {

    interface GetPlacesCalback extends DefaultCallback<PlaceLocations>{};
    void getPlaces(GetPlacesCalback callback);
}
