package com.example.serviciosocial.modalidad;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviciosocial.R;

import java.util.ArrayList;

public class ModalidadAdaptador extends RecyclerView.Adapter<ModalidadAdaptador.MyViewHolder> {

    Context context;
    Activity activity;
    ArrayList id_modalidad, nombre_modalidad;

    ModalidadAdaptador(Activity activity, Context context, ArrayList id_modalidad, ArrayList nombre_modalidad )
    {
        this.activity = activity;
        this.context = context;
        this.id_modalidad = id_modalidad;
        this.nombre_modalidad= nombre_modalidad;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.modalidad_filas,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("recyclerView") int position) {
        holder.id_modalidad.setText(String.valueOf(id_modalidad.get(position)));
        holder.nombre_modalidad.setText(String.valueOf(nombre_modalidad.get(position)));
        holder.ModalidadLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ModificarModalidadActivity.class);
                intent.putExtra("id_modalidad",String.valueOf(id_modalidad.get(position)));
                intent.putExtra("nombre_modalidad",String.valueOf(nombre_modalidad.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id_modalidad.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id_modalidad, nombre_modalidad;
        LinearLayout ModalidadLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_modalidad= itemView.findViewById(R.id.txtIdModalidad);
            nombre_modalidad = itemView.findViewById(R.id.txtNombreModalidad);
            ModalidadLayout = itemView.findViewById(R.id.ModalidadLayout);
        }
    }
}
