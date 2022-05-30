package com.example.serviciosocial.carrera;

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
import com.example.serviciosocial.modalidad.ModalidadAdaptador;

import java.util.ArrayList;

public class CarreraAdaptador extends RecyclerView.Adapter<CarreraAdaptador.MyViewHolder> {

    Context context;
    Activity activity;
    ArrayList id_carrera, nombre_carrera, total_materias;

    public CarreraAdaptador(Context context, Activity activity, ArrayList id_carrera, ArrayList nombre_carrera, ArrayList total_materias) {
        this.context = context;
        this.activity = activity;
        this.id_carrera = id_carrera;
        this.nombre_carrera = nombre_carrera;
        this.total_materias = total_materias;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.carrera_filas, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("recyclerView") int position){
        holder.id_carrera.setText(String.valueOf(id_carrera.get(position)));
        holder.nombre_carrera.setText(String.valueOf(nombre_carrera.get(position)));
        holder.total_materias.setText(String.valueOf(total_materias.get(position)));
        holder.CarreraLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, ModificarCarreraActivity.class);
                intent.putExtra("id_carrera", String.valueOf(id_carrera.get(position)));
                intent.putExtra("nombre_carrera", String.valueOf(nombre_carrera.get(position)));
                intent.putExtra("total_materias", String.valueOf(total_materias.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id_carrera.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id_carrera, nombre_carrera, total_materias;
        LinearLayout CarreraLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_carrera= itemView.findViewById(R.id.txtIdCarrera);
            nombre_carrera = itemView.findViewById(R.id.txtNombreCarrera);
            total_materias = itemView.findViewById(R.id.txtTotalMaterias);
            CarreraLayout = itemView.findViewById(R.id.CarreraLayout);
        }
    }
}
