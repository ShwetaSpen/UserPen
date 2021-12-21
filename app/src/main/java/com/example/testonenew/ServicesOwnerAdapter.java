package com.example.testonenew;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testonenew.model.itemOwnerModel;

import java.util.ArrayList;

public class ServicesOwnerAdapter extends RecyclerView.Adapter<ServicesOwnerAdapter.viewHolder> {

    Context context;
    ArrayList<itemOwnerModel> arrayList;
    private OnItemClickListener mListener;

    public interface  OnItemClickListener{
        void onItemClicked(int position);
    }
    public void setOnItemClicklistener(OnItemClickListener listener)
    {
    mListener = listener;

    }

    public ServicesOwnerAdapter(Context context, ArrayList<itemOwnerModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.service_list_owner, viewGroup, false);
        return new viewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.name.setText(arrayList.get(position).getName());
        holder.icon.setImageResource(arrayList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView icon;
        CardView cv;

        public viewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            icon =itemView.findViewById(R.id.profile);
            cv = itemView.findViewById(R.id.Owner_cardview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClicked(position);
                        }
                    }
                    //        Log.println(getAdapterPosition(),"hi","");
                }
            });

        }
    }

}
