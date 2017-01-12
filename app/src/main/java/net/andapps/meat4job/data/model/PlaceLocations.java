/*
Created by Helm  11/01/2017.
*/


package net.andapps.meat4job.data.model;

import java.util.List;

public class PlaceLocations {

    private List<Geometry> results;


    public List<Geometry> getResults() {
        return results;
    }

    public void setResults(List<Geometry> results) {
        this.results = results;
    }

    public class Geometry {

        LocationContainer geometry;
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LocationContainer getGeometry() {
            return geometry;
        }

        public void setGeometry(LocationContainer geometry) {
            this.geometry = geometry;
        }
    }
}
