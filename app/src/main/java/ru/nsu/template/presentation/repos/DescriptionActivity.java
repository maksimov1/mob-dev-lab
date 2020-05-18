package ru.nsu.template.presentation.repos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;

import ru.nsu.template.R;
import ru.nsu.template.data.model.Description;
import ru.nsu.template.data.model.SearchListItem;

public class DescriptionActivity extends AppCompatActivity {
    public static String ITEM_KEY = "item_key";

    private TextView tvDescription;
    private ImageView ivAvatar;
    private TextView tvName;
    private ProgressBar progressBar;

    private Context context;

    private DescriptionViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        context = this;

        tvDescription = findViewById(R.id.tvDescription);
        ivAvatar = findViewById(R.id.ivAvatar);
        tvName = findViewById(R.id.tvLogin);
        progressBar = findViewById(R.id.progressBar);

        SearchListItem item = (SearchListItem) getIntent().getSerializableExtra(ITEM_KEY);

        viewModel = ViewModelProviders.of(this, new DescriptionViewModelFactory(item)).get(DescriptionViewModel.class);

        viewModel.observeItemLiveData().observe(this, new Observer<SearchListItem>() {
            @Override
            public void onChanged(SearchListItem listItem) {
                Glide.with(context)
                        .load(Uri.parse(listItem.getImageUrl()))
                        .into(ivAvatar);

                tvName.setText(listItem.getName());
            }
        });

        viewModel.observeDescriptionLiveData().observe(this, new Observer<Description>() {
            @Override
            public void onChanged(Description description) {
                tvDescription.setText(description.getDescription());
            }
        });

        viewModel.observeIsLoadingLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                progressBar.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
            }
        });
    }
}
