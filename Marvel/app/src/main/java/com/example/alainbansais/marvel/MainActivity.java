package com.example.alainbansais.marvel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements AsyncResponse {
    ProgressBar loader;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loader = (ProgressBar) findViewById(R.id.progressBar);
        //noinspection ConstantConditions
        loader.setVisibility(VISIBLE);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        //noinspection ConstantConditions
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ApiRequest task = new ApiRequest(this);
        task.execute();
    }

    @Override
    public void processFinish(final List<Character> listResult) {
        loader.setVisibility(GONE);
        adapter = new MyAdapter(this, listResult, new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d("test", listResult.get(position).toString());
            }
        });
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        recyclerView.setAdapter(adapter);
    }
}
