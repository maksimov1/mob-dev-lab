package ru.nsu.template.presentation.repos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import ru.nsu.template.data.model.Description;
import ru.nsu.template.data.model.SearchListItem;
import ru.nsu.template.data.network.LeroyMerlinApi;

public class DescriptionViewModel extends ViewModel {
    private SearchListItem item;

    private LeroyMerlinApi api;

    public LiveData<Description> observeDescriptionLiveData() { return descriptionItemLiveData; }
    private MutableLiveData<Description> descriptionItemLiveData = new MutableLiveData<>();

    public LiveData<SearchListItem> observeItemLiveData() { return itemLiveData; }
    private MutableLiveData<SearchListItem> itemLiveData = new MutableLiveData<>();

    public LiveData<Boolean> observeIsLoadingLiveData() { return isLoadingLiveData; }
    private MutableLiveData<Boolean> isLoadingLiveData = new MutableLiveData<>(false);

    public DescriptionViewModel(SearchListItem item) {
        this.item = item;

        api = LeroyMerlinApi.get();

        init();
    }

    private void init() {
        itemLiveData.setValue(item);

        isLoadingLiveData.setValue(true);
        api.getDescription(item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Description>() {
                    @Override
                    public void onSuccess(Description description) {
                        isLoadingLiveData.setValue(false);
                        descriptionItemLiveData.setValue(description);
                    }

                    @Override
                    public void onError(Throwable e) {
                        isLoadingLiveData.setValue(false);
                    }
                });
    }
}
