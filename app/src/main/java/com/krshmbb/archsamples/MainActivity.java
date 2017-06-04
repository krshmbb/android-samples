package com.krshmbb.archsamples;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends LifecycleActivity {

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @BindView(R.id.list) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        JsonViewModel model = ViewModelProviders.of(this).get(JsonViewModel.class);
        model.getData().observe(this, strings -> {
            adapter = new ListAdapter(strings);
            recyclerView.setAdapter(adapter);
        });
    }
}
