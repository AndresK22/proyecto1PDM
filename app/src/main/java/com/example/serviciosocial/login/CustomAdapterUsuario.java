package com.example.serviciosocial.login;

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

public class CustomAdapterUsuario extends RecyclerView.Adapter<CustomAdapterUsuario.MyViewHolder> {

    Context context;
    Activity activity;
    ArrayList<String> id_usuario,nombre_usuario,contra_usuario,rol_usuario;


    public CustomAdapterUsuario(Activity activity, Context context, ArrayList id_usuario, ArrayList nombre_usuario, ArrayList contra_usuario,ArrayList rol_usuario){
        this.activity = activity;
        this.context = context;
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.contra_usuario = contra_usuario;
        this.rol_usuario = rol_usuario;
    }

    @NonNull
    @Override
    public CustomAdapterUsuario.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.filas_usuario,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterUsuario.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.columnaIdUsuario.setText(String.valueOf(id_usuario.get(position)));
        holder.columnaNombreUsuario.setText(String.valueOf(nombre_usuario.get(position)));
        holder.columnaRolUsuario.setText(String.valueOf(rol_usuario.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ActualizarUsuarioActivity.class);
                intent.putExtra("id_usuario",String.valueOf(id_usuario.get(position)));
                intent.putExtra("nombre_usuario",String.valueOf(nombre_usuario.get(position)));
                intent.putExtra("rol_usuario",String.valueOf(rol_usuario.get(position)));
                intent.putExtra("clave",String.valueOf(contra_usuario.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id_usuario.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView columnaIdUsuario,columnaNombreUsuario,columnaRolUsuario;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            columnaIdUsuario = itemView.findViewById(R.id.columnaIdUsuario);
            columnaNombreUsuario = itemView.findViewById(R.id.columnaNombreUsuario);
            columnaRolUsuario = itemView.findViewById(R.id.columnaRolUsuario);
            mainLayout = itemView.findViewById(R.id.mainLayoutUsuario);
        }
    }
}
