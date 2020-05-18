package ru.nsu.template.presentation.userlist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.nsu.template.R;
import ru.nsu.template.TemplateApplication;
import ru.nsu.template.data.model.SearchList;
import ru.nsu.template.data.model.SearchListItem;

public class UserListViewModel extends ViewModel {
    private SearchList searchList;
    private String query;

    public LiveData<List<SearchListItem>> observeSearchListLiveData() { return userListLiveData; }
    private MutableLiveData<List<SearchListItem>> userListLiveData = new MutableLiveData<>();

    public LiveData<String> observeHeaderLiveData() { return headerLiveData; }
    private MutableLiveData<String> headerLiveData = new MutableLiveData<>();

    UserListViewModel(String query, SearchList searchList) {
        this.query = query;
        this.searchList = searchList;

        userListLiveData.setValue(searchList.getItems());
        headerLiveData.setValue(TemplateApplication.getInstance().getString(R.string.item_list_header, query, searchList.getItems().size()));
    }
}
