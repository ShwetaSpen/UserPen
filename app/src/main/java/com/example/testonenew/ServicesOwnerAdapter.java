package com.example.testonenew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testonenew.model.itemOwnerModel;

import java.util.ArrayList;

public class ServicesOwnerAdapter extends RecyclerView.Adapter<ServicesOwnerAdapter.viewHolder> {

    Context context;
    ArrayList<itemOwnerModel> arrayList;

    public ServicesOwnerAdapter(Context context, ArrayList<itemOwnerModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public ServicesOwnerAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.service_list_owner, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesOwnerAdapter.viewHolder holder, int position) {
        holder.name.setText(arrayList.get(position).getName());
        holder.icon.setImageResource(arrayList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView icon;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            icon =itemView.findViewById(R.id.profile);

        }
    }

}
