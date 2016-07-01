package com.example.alainbansais.characters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alainbansais.R;
import com.example.alainbansais.core.ItemClickListener;
import com.example.alainbansais.model.Character;
import com.squareup.picasso.Picasso;

import java.lang.*;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final List<Character> characterList;
    private final Context context;
    private ItemClickListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView infoName;
        public TextView infoDescription;
        public ImageView img;

        public ViewHolder(View item) {
            super(item);
            img = (ImageView) item.findViewById(R.id.marvel_img);
            infoName = (TextView) item.findViewById(R.id.info_name);
            infoDescription = (TextView) item.findViewById(R.id.info_description);
        }
    }

    public MyAdapter(Context context, List<Character> characterList, ItemClickListener listener) {
        this.characterList = characterList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.my_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.img.setClipToOutline(true);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Character character = characterList.get(position);

        holder.infoName.setText(character.getNickname());
        holder.infoDescription.setText(character.getDescription());

        Picasso.with(context)
               .load(characterList.get(position).getSmallSize())
               .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }
}
