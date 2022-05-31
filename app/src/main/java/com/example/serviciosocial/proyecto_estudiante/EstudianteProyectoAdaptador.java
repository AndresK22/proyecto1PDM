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

public class EstudianteProyectoAdaptador extends RecyclerView.Adapter<EstudianteProyectoAdaptador.MyViewHolder> {

    Context context;
    Activity activity;
    ArrayList id_proyecto, nombre_proyecto;

    EstudianteProyectoAdaptador(Activity activity, Context context,
                                ArrayList id_proyecto,
                                ArrayList nombre_proyecto)
                       {
        this.activity = activity;
        this.context = context;
        this.id_proyecto = id_proyecto;
        this.nombre_proyecto= nombre_proyecto;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.estudiante_proyecto_filas,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.id_pro.setText(String.valueOf(id_proyecto.get(position)));
        holder.nombre_pro.setText(String.valueOf(nombre_proyecto.get(position)));
        holder.EstudianteProyectoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ConsultarEstudiantesAsignados.class);
                intent.putExtra("id_proyecto",String.valueOf(id_proyecto.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
       return id_proyecto.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id_pro, nombre_pro;
        LinearLayout EstudianteProyectoLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_pro = itemView.findViewById(R.id.txtIdProyecto);
            nombre_pro = itemView.findViewById(R.id.txtNombreProyecto);
            EstudianteProyectoLayout = itemView.findViewById(R.id.EstudianteProyectoLayout);
        }
    }
}
