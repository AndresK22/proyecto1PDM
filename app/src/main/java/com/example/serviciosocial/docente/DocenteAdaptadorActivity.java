package com.example.serviciosocial.docente;

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

public class DocenteAdaptadorActivity extends RecyclerView.Adapter<DocenteAdaptadorActivity.MyViewHolder>{

    Context context;
    Activity activity;
    ArrayList lista_dui, lista_nombres, lista_apellidos, lista_email, lista_tel;

    DocenteAdaptadorActivity(Activity activity, Context context, ArrayList dui_docente, ArrayList nombres_docente, ArrayList apellidos_docente, ArrayList email_docente, ArrayList telefono_docente)
    {
        this.activity = activity;
        this.context = context;
        this.lista_dui = dui_docente;
        this.lista_nombres = nombres_docente;
        this.lista_apellidos = apellidos_docente;
        this.lista_email = email_docente;
        this.lista_tel= telefono_docente;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_layout_docente,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("recyclerView") int position) {
        holder.dui_docente.setText(String.valueOf(lista_dui.get(position)));
        holder.nombres_docente.setText(String.valueOf(lista_nombres.get(position)));
        holder.apellidos_docente.setText(String.valueOf(lista_apellidos.get(position)));
        holder.email_docente.setText(String.valueOf(lista_email.get(position)));
        holder.telefono_docente.setText(String.valueOf(lista_tel.get(position)));
        holder.itemsDocente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ModificarDocenteActivity.class); //cambiar al activity modificar
                intent.putExtra("dui_docente",String.valueOf(lista_dui.get(position)));
                intent.putExtra("nombres_docente",String.valueOf(lista_nombres.get(position)));
                intent.putExtra("apellidos_docente",String.valueOf(lista_apellidos.get(position)));
                intent.putExtra("email_docente",String.valueOf(lista_email.get(position)));
                intent.putExtra("telefono_docente",String.valueOf(lista_tel.get(position)));

                activity.startActivityForResult(intent,1);
            }
        });
    }
    @Override
    public int getItemCount() {
        return lista_dui.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView dui_docente, nombres_docente, apellidos_docente, email_docente, telefono_docente;
        LinearLayout itemsDocente;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dui_docente = itemView.findViewById(R.id.txtDuiL);
            nombres_docente = itemView.findViewById(R.id.txtNDocenteL);
            apellidos_docente = itemView.findViewById(R.id.txtADocenteL);
            email_docente = itemView.findViewById(R.id.txtEmailDocenteL);
            telefono_docente = itemView.findViewById(R.id.txtTelefonoDocenteL);

            itemsDocente = itemView.findViewById(R.id.itemsDocente);
        }
    }
}
