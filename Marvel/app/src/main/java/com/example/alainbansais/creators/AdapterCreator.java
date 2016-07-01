package com.example.alainbansais.creators;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alainbansais.R;
import com.example.alainbansais.core.ItemClickListener;
import com.example.alainbansais.model.CreatorMarvel;

import java.util.List;

public class AdapterCreator extends RecyclerView.Adapter<AdapterCreator.ViewHolder> {
    private final List<CreatorMarvel> creators;
    private final Context contextCreator;
    private final ItemClickListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView displayId;
        public TextView infoFirstName;

        public ViewHolder(View item) {
            super(item);
            displayId = (TextView) item.findViewById(R.id.info_id);
            infoFirstName = (TextView) item.findViewById(R.id.info_first_name);
        }
    }

    public AdapterCreator(Context contextCreator, List<CreatorMarvel> creators, ItemClickListener listener) {
        this.creators=creators;
        this.contextCreator = contextCreator;
        this.listener = listener;
    }

    @Override
    public AdapterCreator.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_creator, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterCreator.ViewHolder holder, int position) {
        CreatorMarvel creatorMarvel = creators.get(position);

        holder.displayId.setText(creatorMarvel.getId());
        holder.infoFirstName.setText(creatorMarvel.getFirstName());
    }

    @Override
    public int getItemCount() {
        return creators.size();
    }
}
