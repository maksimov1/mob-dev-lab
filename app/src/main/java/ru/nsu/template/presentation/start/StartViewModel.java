package ru.nsu.template.presentation.start;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import ru.nsu.template.data.model.SearchList;
import ru.nsu.template.data.network.LeroyMerlinApi;

public class StartViewModel extends ViewModel {

    public LiveData<String> observeErrorLiveData() { return errorLiveData; }
    private MutableLiveData<String> errorLiveData = new MutableLiveData<>("");

    public LiveData<SearchList> observeSearchListLiveData() { return searchListLiveData; }
    private MutableLiveData<SearchList> searchListLiveData = new MutableLiveData<>();

    public LiveData<Boolean> observeSearchButtonEnabled() { return searchButtonEnabled; }
    private MutableLiveData<Boolean> searchButtonEnabled = new MutableLiveData<>(false);

    public LiveData<Boolean> observeIsLoadingLiveData() { return isLoadingLiveData; }
    private MutableLiveData<Boolean> isLoadingLiveData = new MutableLiveData<>(false);

    private LeroyMerlinApi api;

    public StartViewModel() {
        api = LeroyMerlinApi.get();
    }

    public void validateSearchQuery(String username) {
        searchButtonEnabled.setValue(!username.equals(""));
    }

    @SuppressLint("CheckResult")
    public void search(String query) {
        isLoadingLiveData.setValue(true);
        api.getSearchList(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<SearchList>() {
                    @Override
                    public void onSuccess(SearchList searchList) {
                        isLoadingLiveData.setValue(false);
                        searchListLiveData.setValue(searchList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        isLoadingLiveData.setValue(false);
                        errorLiveData.setValue(e.getMessage());
                    }
                });
    }
}
