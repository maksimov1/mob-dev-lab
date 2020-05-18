package ru.nsu.template.presentation.userlist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.nsu.template.R;
import ru.nsu.template.data.model.SearchList;
import ru.nsu.template.data.model.SearchListItem;
import ru.nsu.template.presentation.repos.DescriptionActivity;
import ru.nsu.template.presentation.userlist.list.OnUserClickListener;
import ru.nsu.template.presentation.userlist.list.SearchListAdapter;

import static ru.nsu.template.presentation.repos.DescriptionActivity.ITEM_KEY;

public class SearchListActivity extends AppCompatActivity implements OnUserClickListener {
    public static String SEARCH_LIST_KEY = "search_list_key";
    public static String QUERY_KEY = "query_key";

    private RecyclerView rvUserList;
    private SearchListAdapter searchListAdapter;
    private TextView tvHeader;
    private UserListViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getIntent().getExtras();

        setContentView(R.layout.activity_product_list);

        rvUserList = findViewById(R.id.rvUsers);
        tvHeader = findViewById(R.id.tvHeader);
        searchListAdapter = new SearchListAdapter(this);

        SearchList list = new SearchList();
        String query = "";
        if (args != null) {
            list = (SearchList) args.getSerializable(SEARCH_LIST_KEY);
            query = args.getString(QUERY_KEY);
        }

        initList();

        viewModel = ViewModelProviders.of(this, new UserListViewModelFactory(query, list)).get(UserListViewModel.class);

        viewModel.observeHeaderLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvHeader.setText(s);
            }
        });

        viewModel.observeSearchListLiveData().observe(this, new Observer<List<SearchListItem>>() {
            @Override
            public void onChanged(List<SearchListItem> items) {
                searchListAdapter.setItems(items);
            }
        });

    }

    @Override
    public void onUserClick(SearchListItem item) {
        Intent intent = new Intent(SearchListActivity.this, DescriptionActivity.class);
        intent.putExtra(ITEM_KEY, item);

        startActivity(intent);
    }

    private void initList() {
        rvUserList.setLayoutManager(new LinearLayoutManager(this));
        rvUserList.setAdapter(searchListAdapter);
    }
}
