package com.juanurbina.gradeunab;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResourceAdapter extends RecyclerView.Adapter<ResourceAdapter.ViewHolder>{
    private ArrayList<Resource> dataSet;
    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public ResourceAdapter(ArrayList<Resource>dataSet){
        this.dataSet=dataSet;
        this.onClickListener=null;
    }
    @NonNull
    @Override
    public ResourceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_resour_list,parent, false);
        return new ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResourceAdapter.ViewHolder holder, int position) {
        Resource myResource= dataSet.get(position);
        holder.loadItem(myResource);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameResource;
        private Button btnGoToResource;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameResource=itemView.findViewById(R.id.txtItemResoName);
            btnGoToResource=itemView.findViewById(R.id.btnItemResoGo);
        }
        public void loadItem(Resource myResource){
            tvNameResource.setText(myResource.getNameResource());
            btnGoToResource.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onClickListener!=null){
                        onClickListener.onClickGo(myResource);
                    }

                }
            });
        }
    }
    interface OnClickListener{
        void onClickGo(Resource myResource);
    }
}
