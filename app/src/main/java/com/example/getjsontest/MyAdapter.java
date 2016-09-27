package com.example.getjsontest;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<String> titles;
    private View.OnClickListener listener;

    public MyAdapter(final ArrayList<String> titles){
        this.titles = titles;

        listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = ((ViewGroup) view.getParent()).indexOfChild(view);
                String title = titles.get(position);
                Intent intent = new Intent(view.getContext(), WatchlistItem.class);
                intent.putExtra("title", title);
                view.getContext().startActivity(intent);
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titletv;

        public ViewHolder(View itemView){
            super(itemView);
            titletv = (TextView) itemView.findViewById(R.id.titletv);
        }
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int position){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder vh, int i){
        vh.titletv.setText(titles.get(i));
        vh.itemView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount(){
        return titles.size();
    }
}
