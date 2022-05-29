package com.example.serviciosocial.bitacora;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviciosocial.R;
import com.example.serviciosocial.detalleBitacora.ConsultaDetalleBitacoraActivity;

import java.util.ArrayList;


public class BitacoraAdaptadorActivity extends RecyclerView.Adapter<BitacoraAdaptadorActivity.MyViewHolder> {


    Context context;
    Activity activity;
    ArrayList listaid, listaPro, listaCar, listaMes, listaTotal;

    public BitacoraAdaptadorActivity(Activity activity, Context context, ArrayList id1, ArrayList id2, ArrayList id3, ArrayList id4, ArrayList id5)
    {
        this.activity = activity;
        this.context = context;
        this.listaid = id1;
        this.listaPro= id2;
        this.listaCar= id3;
        this.listaMes= id4;
        this.listaTotal= id5;


    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_layout_bitacora,parent,false);

        return new MyViewHolder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("recyclerView") int position) {
        holder.id1.setText(String.valueOf(listaid.get(position)));
        holder.id2.setText(String.valueOf(listaPro.get(position)));
        holder.id3.setText(String.valueOf(listaCar.get(position)));
        holder.id4.setText(String.valueOf(listaMes.get(position)));
        holder.id5.setText(String.valueOf(listaTotal.get(position)));
        holder.itemsBitacora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(context, ModificarBitacoraActivity.class); //cambiar al activity modificar
                    intent.putExtra("id_bitacora",String.valueOf(listaid.get(position)));
                    intent.putExtra("id_proyecto",String.valueOf(listaPro.get(position)));
                    intent.putExtra("carnet",String.valueOf(listaCar.get(position)));
                    intent.putExtra("mes",String.valueOf(listaMes.get(position)));
                    intent.putExtra("total_horas_realizadas",String.valueOf(listaTotal.get(position)));
                    activity.startActivityForResult(intent,1);

            }
        });

        holder.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ConsultaDetalleBitacoraActivity.class); //cambiar al activity modificar
                intent.putExtra("id_bitacora",String.valueOf(listaid.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaid.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id1, id2, id3, id4, id5;
        LinearLayout itemsBitacora;
        Button b;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            b = itemView.findViewById(R.id.btnDetalle);

            id1 = itemView.findViewById(R.id.txtIDL);
            id2 = itemView.findViewById(R.id.txtProyectoL);
            id3 = itemView.findViewById(R.id.txtCarnetL);
            id4 = itemView.findViewById(R.id.txtMesL);
            id5 = itemView.findViewById(R.id.txtHorasL);
            itemsBitacora= itemView.findViewById(R.id.itemsBitacora);

        }
    }


}