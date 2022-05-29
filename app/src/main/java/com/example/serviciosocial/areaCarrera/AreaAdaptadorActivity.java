package com.example.serviciosocial.areaCarrera;

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

public class AreaAdaptadorActivity extends RecyclerView.Adapter<AreaAdaptadorActivity.MyViewHolder> {

    Context context;
    Activity activity;
    ArrayList listaId_area, listaId_carrera, lista_des;

    AreaAdaptadorActivity(Activity activity, Context context, ArrayList id_area, ArrayList id_carrera, ArrayList descrip_area)
    {
        this.activity = activity;
        this.context = context;
        this.listaId_area = id_area;
        this.listaId_carrera = id_carrera;
        this.lista_des= descrip_area;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_layout_area,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("recyclerView") int position) {
        holder.id_area.setText(String.valueOf(listaId_area.get(position)));
        holder.id_carrera.setText(String.valueOf(listaId_carrera.get(position)));
        holder.descrip_area.setText(String.valueOf(lista_des.get(position)));
        holder.itemsArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ModificarAreaActivity.class); //cambiar al activity modificar
                intent.putExtra("id_area",String.valueOf(listaId_area.get(position)));
                intent.putExtra("id_carrera",String.valueOf(listaId_carrera.get(position)));
                intent.putExtra("descrip_area",String.valueOf(lista_des.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaId_area.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id_area, id_carrera, descrip_area;
        LinearLayout itemsArea;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_area = itemView.findViewById(R.id.txtIDArea);
            id_carrera = itemView.findViewById(R.id.txtCarrera);
            descrip_area = itemView.findViewById(R.id.txtDescrip);
            itemsArea = itemView.findViewById(R.id.itemsAreas);
        }
    }
}