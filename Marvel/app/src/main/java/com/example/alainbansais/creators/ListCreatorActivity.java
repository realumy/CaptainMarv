package com.example.alainbansais.creators;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.alainbansais.R;
import com.example.alainbansais.core.AsyncExecutor;
import com.example.alainbansais.core.DividerItemDecoration;
import com.example.alainbansais.core.ItemClickListener;
import com.example.alainbansais.core.MarvelBus;
import com.example.alainbansais.model.CreatorMarvel;

import java.util.List;

import static android.view.View.*;
import static android.view.View.VISIBLE;


public class ListCreatorActivity extends AppCompatActivity implements ListCreatorView {
    private ProgressBar loaderCreator;
    private RecyclerView recyclerViewCreator;
    private ListCreatorPresenter creatorPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creator_list);
        loaderCreator = (ProgressBar) findViewById(R.id.progress_bar);
        recyclerViewCreator = (RecyclerView) findViewById(R.id.recycler_view);
        //noinspection ConstantConditions
        recyclerViewCreator.setHasFixedSize(true);
        recyclerViewCreator.setLayoutManager(new LinearLayoutManager(this));
        creatorPresenter = ListCreatorPresenter.newInstance(this);
        creatorPresenter.register();
        creatorPresenter.load();
    }

    @Override
    protected void onDestroy() {
        creatorPresenter.unregister();
        super.onDestroy();
    }

    @Override
    public void displayLoading() {
        loaderCreator.setVisibility(VISIBLE);
    }

    @Override
    public void displayCreatorList(List<CreatorMarvel> creators) {
        loaderCreator.setVisibility(GONE);
        RecyclerView.Adapter creatorAdapter
                = new AdapterCreator(this, creators, new ItemClickListener() {
            @Override
            public void onItemClick(
                    View v, int position
            ) {

            }
        });
        recyclerViewCreator.addItemDecoration(new DividerItemDecoration(this));
        recyclerViewCreator.setAdapter(creatorAdapter);
        Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();

    }

    @Override
    public void displayEmptyResult() {
        loaderCreator.setVisibility(GONE);
        Toast.makeText(this, "Aucun élément disponible", Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayError() {
        loaderCreator.setVisibility(GONE);
        Toast.makeText(this, "Erreur", Toast.LENGTH_LONG).show();
    }
}
