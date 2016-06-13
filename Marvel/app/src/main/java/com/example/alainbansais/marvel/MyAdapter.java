package com.example.alainbansais.marvel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Character> data;
    private Context context;

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

    public MyAdapter(ArrayList<Character> myData) {
        data = myData;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.my_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.img.setClipToOutline(true);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        context = holder.img.getContext();
        Character character = data.get(position);

        TextView textView = holder.infoName;
        textView.setText(character.getNickname());

        TextView textViewDes = holder.infoDescription;
        textViewDes.setText(character.getDescription());
        Picasso.with(context).load(data.get(position).getSmallSize()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
