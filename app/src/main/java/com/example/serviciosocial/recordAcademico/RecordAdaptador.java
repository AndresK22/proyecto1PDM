package com.example.serviciosocial.recordAcademico;

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

public class RecordAdaptador extends RecyclerView.Adapter<RecordAdaptador.MyViewHolder>{

    Context context;
    Activity activity;
    ArrayList listaId_record, listaCarnet, listaId_area, listaMaterias_aprobadas, listaProgreso, listaPromedio;

    RecordAdaptador(Activity activity, Context context, ArrayList id_record, ArrayList carnet, ArrayList id_area, ArrayList materias_aprobadas, ArrayList progreso, ArrayList promedio)
    {
        this.activity = activity;
        this.context = context;
        this.listaId_record = id_record;
        this.listaCarnet = carnet;
        this.listaId_area = id_area;
        this.listaMaterias_aprobadas = materias_aprobadas;
        this.listaProgreso = progreso;
        this.listaPromedio = promedio;
    }

    @NonNull
    @Override
    public RecordAdaptador.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_layout_record,parent,false);
        return new RecordAdaptador.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordAdaptador.MyViewHolder holder, @SuppressLint("recyclerView") int position) {
        holder.id_record.setText(String.valueOf(listaId_record.get(position)));
        holder.carnet.setText(String.valueOf(listaCarnet.get(position)));
        holder.id_area.setText(String.valueOf(listaId_area.get(position)));
        holder.materias_aprobadas.setText(String.valueOf(listaMaterias_aprobadas.get(position)));
        holder.progreso.setText(String.valueOf(listaProgreso.get(position)));
        holder.promedio.setText(String.valueOf(listaPromedio.get(position)));
        holder.itemsRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ModificarRecordActivity.class); //cambiar al activity modificar
                intent.putExtra("id_record",String.valueOf(listaId_record.get(position)));
                intent.putExtra("carnet",String.valueOf(listaCarnet.get(position)));
                intent.putExtra("id_area",String.valueOf(listaId_area.get(position)));
                intent.putExtra("materias_aprobadas",String.valueOf(listaMaterias_aprobadas.get(position)));
                intent.putExtra("progreso",String.valueOf(listaProgreso.get(position)));
                intent.putExtra("promedio",String.valueOf(listaPromedio.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaId_record.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id_record, carnet, id_area, materias_aprobadas, progreso, promedio;
        LinearLayout itemsRecords;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_record = itemView.findViewById(R.id.txtIdRecord);
            carnet = itemView.findViewById(R.id.txtCarnet_Record);
            id_area = itemView.findViewById(R.id.txtId_area_Record);
            materias_aprobadas = itemView.findViewById(R.id.txtMaterias_aprobadas);
            progreso = itemView.findViewById(R.id.txtProgreso);
            promedio = itemView.findViewById(R.id.txtPromedio);
            itemsRecords = itemView.findViewById(R.id.itemsRecords);
        }
    }
}
