package com.juanurbina.gradeunab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ViewHolder>{
    ArrayList<Reminder> dataSet= new ArrayList<>();
    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener=onClickListener;
    }
    public ReminderAdapter(ArrayList<Reminder> dataSet){
        this.dataSet=dataSet;
        this.onClickListener=null;
    }
    public void setDataSet(ArrayList<Reminder> dataSet){
        this.dataSet=dataSet;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ReminderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_remin_list,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderAdapter.ViewHolder holder, int position) {
        Reminder item=dataSet.get(position);
        holder.enlazar(item);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvRemindName;
        TextView tvRemindBody;
        Button btnRemindEliminar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRemindName=itemView.findViewById(R.id.txtItemRecorName);
            tvRemindBody=itemView.findViewById(R.id.txtItemRecorText);
            btnRemindEliminar=itemView.findViewById(R.id.btnItemRecorDelete);
        }
        public void enlazar(Reminder item){
            tvRemindName.setText(item.getNombreReminder());
            tvRemindBody.setText(item.getTxtReminder());
            btnRemindEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onClickListener != null){
                        onClickListener.onClickEliminar(item);
                    }
                }
            });
        }
    }
    interface OnClickListener{
        void onClickEliminar(Reminder reminder);
    }
}
