/*
Created by Helm  11/01/2017.
*/


package net.andapps.meat4job.ui.fragments.maps;

import net.andapps.meat4job.data.model.PlaceLocations;
import net.andapps.meat4job.data.repositories.MapsDataRepository;

import javax.inject.Inject;

public class MapsPresenter {

    private final MapsDataRepository repository;
    private final MapsView view;

    @Inject
    public MapsPresenter(MapsDataRepository repository, MapsView view) {
        this.repository = repository;
        this.view = view;
    }


    public void onViewCreated() {
        repository.getPlaces(new MapsDataRepository.GetPlacesCalback() {
            @Override
            public void onSuccess(PlaceLocations returnParam) {
                view.addPinsToMap(returnParam);
            }

            @Override
            public void onError(Exception e) {
                //error notification should be here
            }
        });
    }
}
