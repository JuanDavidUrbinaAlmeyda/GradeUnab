package com.juanurbina.gradeunab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotaAdapter extends RecyclerView.Adapter<NotaAdapter.ViewHolder>{
    ArrayList<Nota> dataSet= new ArrayList<>();
    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener=onClickListener;
    }
    public NotaAdapter(ArrayList<Nota> dataSet){
        this.dataSet=dataSet;
        this.onClickListener=null;
    }
    public void setDataSet(ArrayList<Nota> dataSet){
        this.dataSet=dataSet;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public NotaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_sub_list,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotaAdapter.ViewHolder holder, int position) {
        Nota item= dataSet.get(position);
        holder.enlazar(item);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNotaName;
        TextView tvNotaGrade;
        Button btnNotaEliminar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNotaName=itemView.findViewById(R.id.txtItemNotaName);
            tvNotaGrade=itemView.findViewById(R.id.txtItemNotaCalif);
            btnNotaEliminar=itemView.findViewById(R.id.btnItemNotaDel);
        }
        public void enlazar(Nota item){
            tvNotaName.setText(item.getNombreNota());
            tvNotaGrade.setText(String.valueOf(item.getCalifNota()));
            btnNotaEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null){
                        onClickListener.onClickEliminar(item);
                    }
                }
            });
        }
    }
    interface OnClickListener{
        void onClickEliminar(Nota nota);
    }
}
