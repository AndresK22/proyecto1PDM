package com.example.serviciosocial.estado;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.serviciosocial.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EstadoAdaptador extends RecyclerView.Adapter<EstadoAdaptador.MyViewHolder> {

    Context context;
    Activity activity;
    ArrayList id_estado, estado;

    EstadoAdaptador(Activity activity, Context context, ArrayList id_estado, ArrayList estado)
    {
        this.activity = activity;
        this.context = context;
        this.id_estado = id_estado;
        this.estado= estado;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_layout_estado,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("recyclerView") int position) {
        holder.id_estado.setText(String.valueOf(id_estado.get(position)));
        holder.estado.setText(String.valueOf(estado.get(position)));
        holder.itemsEstados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ModificarEstadoActivity.class);
                intent.putExtra("id_estado",String.valueOf(id_estado.get(position)));
                intent.putExtra("estado",String.valueOf(estado.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id_estado.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id_estado, estado;
        LinearLayout itemsEstados;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_estado = itemView.findViewById(R.id.txtIdEstado);
            estado = itemView.findViewById(R.id.txtEstado);
            itemsEstados = itemView.findViewById(R.id.itemsEstados);
        }
    }
}
