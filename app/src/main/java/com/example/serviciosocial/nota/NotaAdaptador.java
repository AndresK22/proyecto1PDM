package com.example.serviciosocial.nota;

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

public class NotaAdaptador extends RecyclerView.Adapter<NotaAdaptador.MyViewHolder>{

    Context context;
    Activity activity;
    ArrayList listaCod_Materia, listaCarnet, listaCalificacion;

    NotaAdaptador(Activity activity, Context context, ArrayList cod_materia, ArrayList carnet, ArrayList calificacion)
    {
        this.activity = activity;
        this.context = context;
        this.listaCod_Materia = cod_materia;
        this.listaCarnet = carnet;
        this.listaCalificacion = calificacion;
    }

    @NonNull
    @Override
    public NotaAdaptador.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_layout_nota,parent,false);
        return new NotaAdaptador.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotaAdaptador.MyViewHolder holder, @SuppressLint("recyclerView") int position) {
        holder.cod_materia.setText(String.valueOf(listaCod_Materia.get(position)));
        holder.carnet.setText(String.valueOf(listaCarnet.get(position)));
        holder.calificacion.setText(String.valueOf(listaCalificacion.get(position)));
        holder.itemsNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ModificarNotaActivity.class); //cambiar al activity modificar
                intent.putExtra("cod_materia",String.valueOf(listaCod_Materia.get(position)));
                intent.putExtra("carnet",String.valueOf(listaCarnet.get(position)));
                intent.putExtra("calificacion",String.valueOf(listaCalificacion.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaCod_Materia.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView cod_materia, carnet, calificacion;
        LinearLayout itemsNotas;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cod_materia = itemView.findViewById(R.id.txtCodMateria_Nota);
            carnet = itemView.findViewById(R.id.txtCarnet_Nota);
            calificacion = itemView.findViewById(R.id.txtCalificacion);
            itemsNotas = itemView.findViewById(R.id.itemsNotas);
        }
    }
}
