package ru.nsu.template.data.model;

public class Description {
    private String description;
    private SearchListItem searchListItem;

    public Description(String description, SearchListItem searchListItem){
        this.description = description;
        this.searchListItem = searchListItem;
    }

    public String getDescription() {
        return description;
    }

    public SearchListItem getSearchListItem() {
        return searchListItem;
    }
}