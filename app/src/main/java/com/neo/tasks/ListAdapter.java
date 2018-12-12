package com.neo.tasks;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.Holder> {//use an adaptor to show list
    public List<String> list;
    private Holder clickHolder;// clicked area
    private MediaPlayer mediaPlayer;

    public ListAdapter(List<String> list, Context context) {
        this.list = list;
        mediaPlayer = MediaPlayer.create(context, R.raw.tsy);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);//get item for list
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
        holder.title.setText(list.get(position));//set item's title
        holder.delete.setOnClickListener(new View.OnClickListener() {//给删除按钮点击事件
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.checkBox.setOnClickListener(new View.OnClickListener() {//item onclick
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView title;//title
        Button delete;//delete button
        LinearLayout mainView;//the whole view
        CheckBox checkBox;

        public Holder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            delete = view.findViewById(R.id.delete);
            mainView = view.findViewById(R.id.item);
            checkBox = view.findViewById(R.id.checkBox);
        }
    }
}