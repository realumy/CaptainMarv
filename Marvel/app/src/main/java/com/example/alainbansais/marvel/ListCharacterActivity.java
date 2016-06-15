package com.example.alainbansais.marvel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ListCharacterActivity extends AppCompatActivity implements ListCharacterView {
    private ProgressBar loader;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ListCharacterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loader = (ProgressBar) findViewById(R.id.progressBar);
        //noinspection ConstantConditions
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        //noinspection ConstantConditions
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter = new ListCharacterPresenter(this);
        presenter.register();
        presenter.load();
    }

    @Override
    protected void onDestroy() {
        presenter.unregister();
        super.onDestroy();
    }

    @Override
    public void displayLoading() {
        loader.setVisibility(VISIBLE);
    }

    @Override
    public void displayCharacterList(final List<Character> characters) {
        loader.setVisibility(GONE);
        adapter = new MyAdapter(this, characters, new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(ListCharacterActivity.this,
                                           DisplayDetailsActivity.class);
                final Character value = characters.get(position);
                intent.putExtra(DisplayDetailsActivity.CHARACTER_EXTRA, value);
                startActivity(intent);
            }
        });
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void displayEmptyResult() {
        loader.setVisibility(GONE);
        Toast.makeText(this, "Aucun élément disponible", Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayError() {
        loader.setVisibility(GONE);
        Toast.makeText(this, "Erreur", Toast.LENGTH_LONG).show();
    }
}