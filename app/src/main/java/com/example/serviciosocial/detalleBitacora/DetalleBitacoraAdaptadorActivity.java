package com.example.serviciosocial.detalleBitacora;

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

public class DetalleBitacoraAdaptadorActivity extends  RecyclerView.Adapter<DetalleBitacoraAdaptadorActivity.MyViewHolder>{

    Context context;
    Activity activity;
    ArrayList lista_id, lista_id_bi, lista_actividad, lista_fecha;


    DetalleBitacoraAdaptadorActivity(Activity activity, Context context, ArrayList f1, ArrayList f2, ArrayList f3 ,ArrayList f4)
    {
        this.activity = activity;
        this.context = context;
        this.lista_id = f1;
        this.lista_id_bi =f2;
        this.lista_actividad = f3;
        this.lista_fecha = f4;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_layout_detalle_bitacora,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("recyclerView") int position) {
        holder.d1.setText(String.valueOf(lista_id.get(position)));
        holder.d2.setText(String.valueOf(lista_actividad.get(position)));
        holder.d3.setText(String.valueOf(lista_fecha.get(position)));

        holder.itemsDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ModificarDetalleBitacoraActivity.class); //cambiar al activity modificar
                intent.putExtra("id_detalle_bitacora",String.valueOf(lista_id.get(position)));
                intent.putExtra("id_bitacora",String.valueOf(lista_id_bi.get(position)));
                intent.putExtra("actividad",String.valueOf(lista_actividad.get(position)));
                intent.putExtra("fecha_bitacora",String.valueOf(lista_fecha.get(position)));

                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView d1, d2, d3;
        LinearLayout itemsDetalle;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            d1 = itemView.findViewById(R.id.txtDetalleL);
            d2 = itemView.findViewById(R.id.txtActividadL);
            d3 = itemView.findViewById(R.id.txtFechaL);


            itemsDetalle = itemView.findViewById(R.id.itemsDetalleBitacora);
        }
    }
}