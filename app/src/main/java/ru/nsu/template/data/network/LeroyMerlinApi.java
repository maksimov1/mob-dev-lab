package ru.nsu.template.data.network;

import io.reactivex.Single;
import ru.nsu.template.data.model.Description;
import ru.nsu.template.data.model.SearchList;
import ru.nsu.template.data.model.SearchListItem;
import ru.nsu.template.data.network.parser.LeroyMerlinParser;

public class LeroyMerlinApi {

    private static final LeroyMerlinApi API = new LeroyMerlinApi();

    private LeroyMerlinApi() {}

    public static LeroyMerlinApi get() {
        return API;
    }

    public Single<SearchList> getSearchList(String query) {
        return Single.defer(() -> Single.just(LeroyMerlinParser.findItems(query)));
    }

    public Single<Description> getDescription(SearchListItem item) {
        return Single.defer(() -> Single.just(LeroyMerlinParser.getFullItem(item)));
    }
}
