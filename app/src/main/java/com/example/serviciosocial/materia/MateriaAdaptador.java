package com.example.serviciosocial.materia;

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

public class MateriaAdaptador extends RecyclerView.Adapter<MateriaAdaptador.MyViewHolder>{

    Context context;
    Activity activity;
    ArrayList listaCod_materia, listaId_area, listaNombre_materia;

    MateriaAdaptador(Activity activity, Context context, ArrayList cod_materia, ArrayList id_area, ArrayList nombre_materia)
    {
        this.activity = activity;
        this.context = context;
        this.listaCod_materia = cod_materia;
        this.listaId_area = id_area;
        this.listaNombre_materia= nombre_materia;
    }

    @NonNull
    @Override
    public MateriaAdaptador.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_layout_materia,parent,false);
        return new MateriaAdaptador.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MateriaAdaptador.MyViewHolder holder, @SuppressLint("recyclerView") int position) {
        holder.cod_materia.setText(String.valueOf(listaCod_materia.get(position)));
        holder.nombre_materia.setText(String.valueOf(listaNombre_materia.get(position)));
        holder.id_area.setText(String.valueOf(listaId_area.get(position)));
        holder.itemsMaterias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ModificarMateriaActivity.class); //cambiar al activity modificar
                intent.putExtra("cod_materia",String.valueOf(listaCod_materia.get(position)));
                intent.putExtra("id_area",String.valueOf(listaId_area.get(position)));
                intent.putExtra("nombre_materia",String.valueOf(listaNombre_materia.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaCod_materia.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView cod_materia, id_area, nombre_materia;
        LinearLayout itemsMaterias;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cod_materia = itemView.findViewById(R.id.txtCodMateria);
            id_area = itemView.findViewById(R.id.txtId_area_Materia);
            nombre_materia = itemView.findViewById(R.id.txtMateria);
            itemsMaterias = itemView.findViewById(R.id.itemsMaterias);
        }
    }
}
