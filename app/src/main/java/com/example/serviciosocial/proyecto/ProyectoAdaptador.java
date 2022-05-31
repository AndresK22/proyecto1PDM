package com.example.serviciosocial.proyecto;

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

public class ProyectoAdaptador extends RecyclerView.Adapter<ProyectoAdaptador.MyViewHolder> {

    Context context;
    Activity activity;
    ArrayList id_proyecto, id_categoria, id_modalidad,dui_docente,id_estado,id_carrera,id_area,nombre_proyecto,descripcion_proyecto,lugar,requisito_nota;

    ProyectoAdaptador(Activity activity, Context context, ArrayList id_proyecto,ArrayList id_categoria, ArrayList id_modalidad,ArrayList dui_docente,ArrayList id_estado,ArrayList id_carrera,ArrayList id_area,ArrayList nombre_proyecto,ArrayList descripcion_proyecto,ArrayList lugar,ArrayList requisito_nota)
    {
        this.activity = activity;
        this.context = context;
        this.id_proyecto = id_proyecto;
        this.id_categoria = id_categoria;
        this.id_modalidad= id_modalidad;
        this.dui_docente=dui_docente;
        this.id_estado=id_estado;
        this.id_carrera=id_carrera;
        this.id_area=id_area;
        this.nombre_proyecto=nombre_proyecto;
        this.descripcion_proyecto=descripcion_proyecto;
        this.lugar=lugar;
        this.requisito_nota=requisito_nota;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.proyecto_filas,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("recyclerView") int position) {
        holder.id_proyecto.setText(String.valueOf(id_proyecto.get(position)));
        holder.id_categoria.setText(String.valueOf(id_categoria.get(position)));
        holder.id_modalidad.setText(String.valueOf(id_modalidad.get(position)));
        holder.dui_docente.setText(String.valueOf(dui_docente.get(position)));
        holder.id_estado.setText(String.valueOf(id_estado.get(position)));
        holder.id_carrera.setText(String.valueOf(id_carrera.get(position)));
        holder.id_area.setText(String.valueOf(id_area.get(position)));
        holder.nombre_proyecto.setText(String.valueOf(nombre_proyecto.get(position)));
        holder.descripcion_proyecto.setText(String.valueOf(descripcion_proyecto.get(position)));
        holder.lugar.setText(String.valueOf(lugar.get(position)));
        holder.requisito_nota.setText(String.valueOf(requisito_nota.get(position)));
        holder.ProyectoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ModificarProyectoActivity.class); //cambiar al activity modificar
                intent.putExtra("id_proyecto",String.valueOf(id_proyecto.get(position)));
                intent.putExtra("id_categoria",String.valueOf(id_categoria.get(position)));
                intent.putExtra("id_modalidad",String.valueOf(id_modalidad.get(position)));
                intent.putExtra("dui_docente",String.valueOf(dui_docente.get(position)));
                intent.putExtra("id_estado",String.valueOf(id_estado.get(position)));
                intent.putExtra("id_carrera",String.valueOf(id_carrera.get(position)));
                intent.putExtra("id_area",String.valueOf(id_area.get(position)));
                intent.putExtra("nombre_proyecto",String.valueOf(nombre_proyecto.get(position)));
                intent.putExtra("descripcion_proyecto",String.valueOf(descripcion_proyecto.get(position)));
                intent.putExtra("lugar",String.valueOf(lugar.get(position)));
                intent.putExtra("requisito_nota",String.valueOf(requisito_nota.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id_proyecto.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id_proyecto, id_categoria, id_modalidad,dui_docente,id_estado,id_carrera,id_area,nombre_proyecto,descripcion_proyecto,lugar,requisito_nota;
        LinearLayout ProyectoLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_proyecto = itemView.findViewById(R.id.txtIdProyectoP);
            id_categoria = itemView.findViewById(R.id.txtIdCateP);
            id_modalidad = itemView.findViewById(R.id.txtIdModP);
            dui_docente=itemView.findViewById(R.id.txtDuiDocenteP);
            id_estado=itemView.findViewById(R.id.txtIdEstadoP);
            id_carrera=itemView.findViewById(R.id.txtCarreraP);
            id_area=itemView.findViewById(R.id.txtAreaP);
            nombre_proyecto=itemView.findViewById(R.id.editTextProyectoP);
            descripcion_proyecto=itemView.findViewById(R.id.txtDescripcionP);
            lugar=itemView.findViewById(R.id.txtLugarP);
            requisito_nota=itemView.findViewById(R.id.txtRequisitoP);
            ProyectoLayout = itemView.findViewById(R.id.ProyectoLayout);
        }
    }
}