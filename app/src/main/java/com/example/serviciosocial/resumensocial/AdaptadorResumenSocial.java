package com.example.serviciosocial.resumensocial;

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

public class AdaptadorResumenSocial extends RecyclerView.Adapter<AdaptadorResumenSocial.MyViewHolder> {
    Context context;
    Activity activity;
    ArrayList listaId_resumen, listaDui_docente, listaCarnet, listaFechaAE, listaFechaEC, listaObservaciones;

    AdaptadorResumenSocial(Activity activity, Context context,  ArrayList listaId_resumen, ArrayList listaDui_docente, ArrayList listaCarnet, ArrayList listaFechaAE, ArrayList listaFechaEC, ArrayList listaObservaciones) {
        this.context = context;
        this.activity = activity;
        this.listaId_resumen = listaId_resumen;
        this.listaDui_docente = listaDui_docente;
        this.listaCarnet = listaCarnet;
        this.listaFechaAE = listaFechaAE;
        this.listaFechaEC = listaFechaEC;
        this.listaObservaciones = listaObservaciones;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.resumensocial_filas, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("recyclerView") int position) {
        holder.id_resumen.setText(String.valueOf(listaId_resumen.get(position)));
        holder.dui_docente.setText(String.valueOf(listaDui_docente.get(position)));
        holder.carnet.setText(String.valueOf(listaCarnet.get(position)));
        holder.fecha_apertura.setText(String.valueOf(listaFechaAE.get(position)));
        holder.fecha_emision.setText(String.valueOf(listaFechaEC.get(position)));
        holder.observaciones.setText(String.valueOf(listaObservaciones.get(position)));
        holder.itemsResumen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, ModificarResumenActivity.class);
                intent.putExtra("id_resumen", String.valueOf(listaId_resumen.get(position)));
                intent.putExtra("dui_docente", String.valueOf(listaDui_docente.get(position)));
                intent.putExtra("carnet", String.valueOf(listaCarnet.get(position)));
                intent.putExtra("fecha_apertura_expediente", String.valueOf(listaFechaAE.get(position)));
                intent.putExtra("fecha_emision_certificado", String.valueOf(listaFechaEC.get(position)));
                intent.putExtra("observaciones", String.valueOf(listaObservaciones.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listaId_resumen.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView id_resumen, dui_docente, carnet, fecha_apertura, fecha_emision, observaciones;
        LinearLayout itemsResumen;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            id_resumen = itemView.findViewById(R.id.txtId_det_res);
            dui_docente = itemView.findViewById(R.id.txtId_resumen);
            carnet = itemView.findViewById(R.id.txtHoras_asignadas);
            fecha_apertura = itemView.findViewById(R.id.txtFechaApertura);
            fecha_emision = itemView.findViewById(R.id.txtId_proyecto);
            observaciones = itemView.findViewById(R.id.txtMonto);
            itemsResumen = itemView.findViewById(R.id.LayoutResumenSocial);
        }
    }
}
