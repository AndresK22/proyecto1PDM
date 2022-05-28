package com.example.serviciosocial.categoria;


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

public class CategoriaAdpatador extends RecyclerView.Adapter<CategoriaAdpatador.MyViewHolder> {

    Context context;
    Activity activity;
    ArrayList id_categoria, nombre_categoria;

    CategoriaAdpatador(Activity activity, Context context,
                       ArrayList id_categoria,
                       ArrayList nombre_categoria)
                       {
        this.activity = activity;
        this.context = context;
        this.id_categoria = id_categoria;
        this.nombre_categoria= nombre_categoria;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.categoria_filas,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.id_cat.setText(String.valueOf(id_categoria.get(position)));
        holder.nombre_cat.setText(String.valueOf(nombre_categoria.get(position)));
        holder.CategoriaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ActualizarCategoriaActivity.class);
                intent.putExtra("id_categoria",String.valueOf(id_categoria.get(position)));
                intent.putExtra("nombre_categoria",String.valueOf(nombre_categoria.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
       return id_categoria.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id_cat, nombre_cat;
        LinearLayout CategoriaLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_cat = itemView.findViewById(R.id.txtIdCategoria);
            nombre_cat = itemView.findViewById(R.id.txtNombreCategoria);
            CategoriaLayout = itemView.findViewById(R.id.CategoriaLayout);
        }
    }
}
