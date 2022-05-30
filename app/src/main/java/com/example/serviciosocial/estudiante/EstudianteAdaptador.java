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

import java.util.ArrayList;

public class EstudianteAdaptador extends RecyclerView.Adapter<EstudianteAdaptador.MyViewHolder>{

    Context context;
    Activity activity;
    ArrayList listCarnet, listNombres, listApellidos, listEmail, listTelefono, listDomicilio, listDui;

    public EstudianteAdaptador(Activity activity, Context context,  ArrayList carnet, ArrayList nombres, ArrayList apellidos, ArrayList email, ArrayList telefono, ArrayList domicilio, ArrayList dui) {
        this.context = context;
        this.activity = activity;
        this.listCarnet = carnet;
        this.listNombres = nombres;
        this.listApellidos = apellidos;
        this.listEmail = email;
        this.listTelefono = telefono;
        this.listDomicilio = domicilio;
        this.listDui = dui;
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
        holder.carnet.setText(String.valueOf(listCarnet.get(position)));
        holder.nombres.setText(String.valueOf(listNombres.get(position)));
        holder.apellidos.setText(String.valueOf(listApellidos.get(position)));
        holder.email.setText(String.valueOf(listEmail.get(position)));
        holder.telefono.setText(String.valueOf(listTelefono.get(position)));
        holder.domicilio.setText(String.valueOf(listDomicilio.get(position)));
        holder.dui.setText(String.valueOf(listDui.get(position)));
        holder.EstudianteLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, ModificarEstudianteActivity.class);
                intent.putExtra("carnet", String.valueOf(listCarnet.get(position)));
                intent.putExtra("nombres_estudiante", String.valueOf(listNombres.get(position)));
                intent.putExtra("apellidos_estudiante", String.valueOf(listApellidos.get(position)));
                intent.putExtra("email_estudiante", String.valueOf(listEmail.get(position)));
                intent.putExtra("telefono_estudiante", String.valueOf(listTelefono.get(position)));
                intent.putExtra("domicilio", String.valueOf(listDomicilio.get(position)));
                intent.putExtra("dui", String.valueOf(listDui.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listCarnet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView carnet, nombres, apellidos, email, telefono, domicilio, dui;
        LinearLayout EstudianteLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            carnet = itemView.findViewById(R.id.txtCarnetEstudiante);
            nombres = itemView.findViewById(R.id.txtNombresEstudiante);
            apellidos = itemView.findViewById(R.id.txtApellidosEstudiante);
            email = itemView.findViewById(R.id.txtEmailEstudiante);
            telefono = itemView.findViewById(R.id.txtTelefonoE);
            domicilio = itemView.findViewById(R.id.txtDomicilio);
            dui = itemView.findViewById(R.id.txtDuiE);
            EstudianteLayout = itemView.findViewById(R.id.EstudianteLayout);
        }
    }
}
