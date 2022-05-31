package com.example.serviciosocial.proyecto_estudiante;


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
import com.example.serviciosocial.categoria.ActualizarCategoriaActivity;

import java.util.ArrayList;

public class EstudiantesAdaptador extends RecyclerView.Adapter<EstudiantesAdaptador.MyViewHolder> {

    Context context;
    Activity activity;
    ArrayList carnet,nombre_est;
    String id_proyecto;

    EstudiantesAdaptador(Activity activity, Context context,
                         ArrayList carnet,
                         ArrayList nombre_est,
                         String id_proyecto)
                       {
        this.activity = activity;
        this.context = context;
        this.carnet= carnet;
        this.nombre_est= nombre_est;
        this.id_proyecto=id_proyecto;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.estudiante_asignados_filas,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.id_carnet.setText(String.valueOf(carnet.get(position)));
        holder.nombre_est.setText(String.valueOf(nombre_est.get(position)));
        holder.EstudiantesAsignadosLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ModificarEstudiantesAsignadosActivity.class);
                intent.putExtra("carnet",String.valueOf(carnet.get(position)));
                intent.putExtra("nombres_estudiante",String.valueOf(nombre_est.get(position)));
                intent.putExtra("id_proyecto",id_proyecto);
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
       return carnet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id_carnet, nombre_est;
        LinearLayout EstudiantesAsignadosLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_carnet = itemView.findViewById(R.id.txtCarnetP);
            nombre_est = itemView.findViewById(R.id.txtNombresEstudiante);
            EstudiantesAsignadosLayout = itemView.findViewById(R.id.EstudiantesAsignadosLayout);
        }
    }
}
