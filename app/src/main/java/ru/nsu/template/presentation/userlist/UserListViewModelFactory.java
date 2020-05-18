package ru.nsu.template.presentation.userlist;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.nsu.template.data.model.SearchList;

public class UserListViewModelFactory implements ViewModelProvider.Factory {
    private String query;
    private SearchList searchList;

    public UserListViewModelFactory(String query, SearchList searchList) {
        this.query = query;
        this.searchList = searchList;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UserListViewModel(query, searchList);
    }
}
