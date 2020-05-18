package ru.nsu.template.presentation.repos;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.nsu.template.data.model.SearchListItem;

public class DescriptionViewModelFactory implements ViewModelProvider.Factory {
    private SearchListItem searchListItem;

    public DescriptionViewModelFactory(SearchListItem searchListItem) {
        this.searchListItem = searchListItem;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DescriptionViewModel(searchListItem);
    }
}
