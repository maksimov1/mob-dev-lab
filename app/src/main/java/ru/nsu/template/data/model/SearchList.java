package ru.nsu.template.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class SearchList implements Serializable {

    public SearchList() {
        this.items = Collections.emptyList();
    }

    @SerializedName("items")
    private List<SearchListItem> items;

    public List<SearchListItem> getItems() {
        return items;
    }

    public void setItems(List<SearchListItem> items) {
        this.items = items;
    }
}
