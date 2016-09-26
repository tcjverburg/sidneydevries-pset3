package com.example.getjsontest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<String> titles;
    private ArrayList<String> years;
    private ArrayList<String> plots;
    private View.OnClickListener listener;

    public MyAdapter(ArrayList<String> titles, ArrayList<String> years, ArrayList<String> plots){
        this.titles = titles;
        this.years = years;
        this.plots = plots;
//        listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int position = ((ViewGroup) view.getParent()).indexOfChild(view);
//                Intent goToWatchlistItem = new Intent(this, WatchlistItem.class);
//                goToWatchlistItem.putExtra("movieclicked", position);
//                startActivity(goToWatchlistItem);
//            }
//        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titletv;
        public TextView yeartv;
        public TextView plottv;
        public View.OnClickListener myListener;

        public ViewHolder(View itemView){
            super(itemView);
            titletv = (TextView) itemView.findViewById(R.id.titletv);
            yeartv = (TextView) itemView.findViewById(R.id.yeartv);
            plottv = (TextView) itemView.findViewById(R.id.plottv);
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
        vh.yeartv.setText(years.get(i));
        vh.plottv.setText(plots.get(i));
        vh.itemView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount(){
        return titles.size();
    }


}
