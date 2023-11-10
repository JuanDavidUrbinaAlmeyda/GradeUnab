package com.juanurbina.gradeunab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MateriaAdapter extends RecyclerView.Adapter<MateriaAdapter.ViewHolder> {


    ArrayList<Materia> dataSet = new ArrayList<>();
    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public MateriaAdapter(ArrayList<Materia> dataSet) {
        this.dataSet = dataSet;
        this.onClickListener = null;
    }

    public void setDataSet(ArrayList<Materia> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_grad_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Materia item = dataSet.get(position);
        holder.enlazar(item);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        Button btnEliminar;
        Button btnDetalle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_item_materia_name);
            btnEliminar = itemView.findViewById(R.id.btnItemSubDelete);
            btnDetalle = itemView.findViewById(R.id.btn_detalle_materia);

        }

        public void enlazar(Materia item) {
            tvName.setText(item.getNombreMateria());
            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (onClickListener != null) {
                        onClickListener.onClickEliminar(item);
                    }

                }
            });
            btnDetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null){
                        onClickListener.onClickDetalle(item);
                    }
                }
            });

        }
    }


    interface OnClickListener {
        void onClickEliminar(Materia materia);

        void onClickDetalle(Materia materia);
    }
}
