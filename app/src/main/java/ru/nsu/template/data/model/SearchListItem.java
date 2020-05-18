package ru.nsu.template.data.model;

import android.os.Parcelable;

import java.io.Serializable;

public class SearchListItem implements Serializable {
    private String name;
    private String url;
    private String imageUrl;
    private String weight;
    private String weightMetric;
    private String price;

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getWeight() {
        return weight;
    }

    public String getWeightMetric() {
        return weightMetric;
    }

    public String getPrice() {
        return price;
    }

    public SearchListItem(String name, String url, String imageUrl, String weight,
                          String weightMetric, String price){
        this.name = name;
        this.url = url;
        this.imageUrl = imageUrl;
        this.weight = weight;
        this.weightMetric = weightMetric;
        this.price = price;
    }

    @Override
    public String toString(){
        return name + " " +
                url + " " +
                imageUrl + " " +
                weight + " " +
                weightMetric + " " +
                price;
    }
}