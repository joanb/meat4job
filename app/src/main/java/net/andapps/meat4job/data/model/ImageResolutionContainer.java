/*
Created by Helm  14/01/2017.
*/


package net.andapps.meat4job.data.model;

import com.google.gson.annotations.SerializedName;

public class ImageResolutionContainer {

    Images images;

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public class PictureUrl {

        String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public class Images {

        @SerializedName("standard_resolution")
        PictureUrl images;


        public PictureUrl getStandard() {
            return images;
        }

        public void setStandard(PictureUrl standard) {
            images = standard;
        }
    }
}
