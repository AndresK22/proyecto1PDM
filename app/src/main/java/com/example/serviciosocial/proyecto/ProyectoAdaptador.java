package com.example.serviciosocial.proyecto;

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

public class ProyectoAdaptador extends RecyclerView.Adapter<ProyectoAdaptador.MyViewHolder> {

    Context context;
    Activity activity;
    ArrayList id_proyecto,nombre_proyecto;

    ProyectoAdaptador(Activity activity, Context context, ArrayList id_proyecto,ArrayList nombre_proyecto)
    {
        this.activity = activity;
        this.context = context;
        this.id_proyecto = id_proyecto;
        this.nombre_proyecto = nombre_proyecto;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.proyecto_filas,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("recyclerView") int position) {
        holder.id_proyecto.setText(String.valueOf(id_proyecto.get(position)));
        holder.nombre_proyecto.setText(String.valueOf(nombre_proyecto.get(position)));
        holder.ProyectoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ModificarProyectoActivity.class);
                intent.putExtra("id_proyecto",String.valueOf(id_proyecto.get(position)));
                intent.putExtra("nombre_modalidad",String.valueOf(nombre_proyecto.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id_proyecto.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id_proyecto, nombre_proyecto;
        LinearLayout ProyectoLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id_proyecto= itemView.findViewById(R.id.txtIdProyecto);
            nombre_proyecto = itemView.findViewById(R.id.editTextProyecto);
            ProyectoLayout = itemView.findViewById(R.id.ProyectoLayout);
        }
    }
}
