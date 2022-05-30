package com.example.serviciosocial.estudiante;

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
import com.example.serviciosocial.carrera.CarreraAdaptador;
import com.example.serviciosocial.carrera.ModificarCarreraActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EstudianteAdaptador extends RecyclerView.Adapter<EstudianteAdaptador.MyViewHolder>{

    Context context;
    Activity activity;
    ArrayList carnet, nombres, apellidos, email, telefono, domicilio, dui;

    public EstudianteAdaptador(Context context, Activity activity, ArrayList carnet, ArrayList nombres, ArrayList apellidos, ArrayList email, ArrayList telefono, ArrayList domicilio, ArrayList dui) {
        this.context = context;
        this.activity = activity;
        this.carnet = carnet;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.domicilio = domicilio;
        this.dui = dui;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.estudiante_filas, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("recyclerView") int position) {
        holder.carnet.setText(String.valueOf(carnet.get(position)));
        holder.nombres.setText(String.valueOf(nombres.get(position)));
        holder.apellidos.setText(String.valueOf(apellidos.get(position)));
        holder.email.setText(String.valueOf(email.get(position)));
        holder.telefono.setText(String.valueOf(telefono.get(position)));
        holder.domicilio.setText(String.valueOf(domicilio.get(position)));
        holder.dui.setText(String.valueOf(dui.get(position)));
        holder.EstudianteLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, ModificarEstudianteActivity.class);
                intent.putExtra("carnet", String.valueOf(carnet.get(position)));
                intent.putExtra("nombres_estudiante", String.valueOf(nombres.get(position)));
                intent.putExtra("apellidos_estudiante", String.valueOf(apellidos.get(position)));
                intent.putExtra("email_estudiante", String.valueOf(email.get(position)));
                intent.putExtra("telefono_estudiante", String.valueOf(telefono.get(position)));
                intent.putExtra("domicilio", String.valueOf(domicilio.get(position)));
                intent.putExtra("dui", String.valueOf(dui.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }


    @Override
    public int getItemCount() {
        return carnet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView carnet, nombres, apellidos, email, telefono, domicilio, dui;
        LinearLayout EstudianteLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            carnet = itemView.findViewById(R.id.txtCarnetEstudiante);
            nombres = itemView.findViewById(R.id.txtNombresEstudiante);
            apellidos = itemView.findViewById(R.id.txtApellidosCarrera);
            email = itemView.findViewById(R.id.txtEmailEstudiante);
            telefono = itemView.findViewById(R.id.txtTelefonoE);
            domicilio = itemView.findViewById(R.id.txtDomicilio);
            dui = itemView.findViewById(R.id.txtDuiE);
            EstudianteLayout = itemView.findViewById(R.id.EstudianteLayout);
        }
    }
}
