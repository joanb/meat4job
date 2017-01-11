/*
Created by Helm  11/01/2017.
*/


package net.andapps.meat4job.ui.fragments.maps;

import net.andapps.meat4job.data.model.PlaceLocations;

public interface MapsView {
    void initializeInjection();

    void addPinsToMap(PlaceLocations locations);
}
