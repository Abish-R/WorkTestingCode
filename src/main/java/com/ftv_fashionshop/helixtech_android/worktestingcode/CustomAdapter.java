package com.ftv_fashionshop.helixtech_android.worktestingcode;

/**
 * Created by helixtech-android on 4/6/16.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<Pogo> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewVersion,textViewVersion1;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textViewVersion = (TextView) itemView.findViewById(R.id.textViewVersion);
            this.textViewVersion1 = (TextView) itemView.findViewById(R.id.textViewVersion1);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public CustomAdapter(ArrayList<Pogo> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewVersion = holder.textViewVersion;
        TextView textViewVersion1 = holder.textViewVersion1;
        ImageView imageView = holder.imageViewIcon;

        //Pogo p = new Pogo();
        textViewName.setText(dataSet.get(listPosition).getOne());
        textViewVersion.setText(dataSet.get(listPosition).getTwo());
        textViewVersion1.setText(dataSet.get(listPosition).getThree());
        //imageView.setImageResource(dataSet.get(listPosition));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
