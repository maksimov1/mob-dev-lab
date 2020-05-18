package ru.nsu.template.data.network.parser;

import ru.nsu.template.data.model.Description;
import ru.nsu.template.data.model.SearchList;
import ru.nsu.template.data.model.SearchListItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class LeroyMerlinParser {
    public static SearchList findItems(String query) throws IOException {
        Document document = Jsoup.connect("https://leroymerlin.ru/search/?q=" + query).get();
        Elements products = document.select(".product-name");
        Elements prices = document.select(".main-value-part");
        Elements pageWeights = document.select(".madein__text");
        Elements pageWeightMetrics = document.select(".ui-product-card-info__name");
        Elements pageImage = document.select(".image-section-image");
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> links = new ArrayList<>();
        ArrayList<Double> doublePrices = new ArrayList<>();
        ArrayList<String> weights = new ArrayList<>();
        ArrayList<String> weightMetrics = new ArrayList<>();
        ArrayList<String> imageUrls = new ArrayList<>();

        for (int i = 0; i < products.size(); i++){
            names.add(products.get(i).text());
            links.add("https://leroymerlin.ru" + products.get(i).select("a[href]").attr("href"));
            doublePrices.add(Double.valueOf(prices.get(i).attr("content").replace(',', '.').replaceAll("\\s","")));
            weights.add(pageWeights.get(3 * i + 2).text());
            weightMetrics.add(pageWeightMetrics.get(3 * i + 2).text());
            imageUrls.add(pageImage.get(3 * i).attr("srcset"));
        }

        ArrayList<SearchListItem> items = new ArrayList<>();
        for (int i = 0; i < 30 && i < names.size(); i++){
            items.add(new SearchListItem(names.get(i), links.get(i), imageUrls.get(i),  weights.get(i), weightMetrics.get(i), doublePrices.get(i).toString()));
        }
        SearchList searchList = new SearchList();
        searchList.setItems(items);
        return searchList;
    }

    public static Description getFullItem(SearchListItem item) throws IOException{
        Document document = Jsoup.connect(item.getUrl()).get();
        Element description = document.selectFirst(".pdp-section--product-description p");

        return new Description(description.text(), item);
    }
}
