package com.example.serviciosocial.detalleResumenSocial;

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
import com.example.serviciosocial.resumensocial.AdaptadorResumenSocial;
import com.example.serviciosocial.resumensocial.ModificarResumenActivity;

import java.util.ArrayList;

public class AdaptadorDetalleResumen extends RecyclerView.Adapter<AdaptadorDetalleResumen.MyViewHolder> {
    Context context;
    Activity activity;
    ArrayList listaId_detalle, listaId_resumen, listaId_proyecto, listaFechaI, listaFechaF, listaHoras, listaMonto, listaBenefIn, getListaBenefDir, listaEstado;

    public AdaptadorDetalleResumen(Activity activity, Context context, ArrayList listaId_detalle, ArrayList listaId_resumen, ArrayList listaId_proyecto, ArrayList listaFechaI, ArrayList listaFechaF, ArrayList listaHoras, ArrayList listaMonto, ArrayList listaBenefIn, ArrayList getListaBenefDir, ArrayList listaEstado) {
        this.context = context;
        this.activity = activity;
        this.listaId_detalle = listaId_detalle;
        this.listaId_resumen = listaId_resumen;
        this.listaId_proyecto = listaId_proyecto;
        this.listaFechaI = listaFechaI;
        this.listaFechaF = listaFechaF;
        this.listaHoras = listaHoras;
        this.listaMonto = listaMonto;
        this.listaBenefIn = listaBenefIn;
        this.getListaBenefDir = getListaBenefDir;
        this.listaEstado = listaEstado;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.detalleresumensocial_filas, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("recyclerView") int position) {
        holder.id_det.setText(String.valueOf(listaId_detalle.get(position)));
        holder.id_resumen.setText(String.valueOf(listaId_resumen.get(position)));
        holder.id_proyecto.setText(String.valueOf(listaId_proyecto.get(position)));
        holder.fecha_inicio.setText(String.valueOf(listaFechaI.get(position)));
        holder.fecha_final.setText(String.valueOf(listaFechaF.get(position)));
        holder.horas_asignadas.setText(String.valueOf(listaHoras.get(position)));
        holder.monto.setText(String.valueOf(listaMonto.get(position)));
        holder.benef_indir.setText(String.valueOf(listaBenefIn.get(position)));
        holder.benef_dir.setText(String.valueOf(getListaBenefDir.get(position)));
        holder.estado_det.setText(String.valueOf(listaEstado.get(position)));
        holder.itemsDetalleResumen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, ModificarDetalleResumenActivity.class);
                intent.putExtra("id_det_res", String.valueOf(listaId_detalle.get(position)));
                intent.putExtra("id_resumen", String.valueOf(listaId_resumen.get(position)));
                intent.putExtra("id_proyecto", String.valueOf(listaId_proyecto.get(position)));
                intent.putExtra("fecha_inicio", String.valueOf(listaFechaI.get(position)));
                intent.putExtra("fecha_final", String.valueOf(listaFechaF.get(position)));
                intent.putExtra("horas_asignadas", String.valueOf(listaHoras.get(position)));
                intent.putExtra("monto", String.valueOf(listaMonto.get(position)));
                intent.putExtra("benef_indir", String.valueOf(listaBenefIn.get(position)));
                intent.putExtra("benef_dir", String.valueOf(getListaBenefDir.get(position)));
                intent.putExtra("estado_det", String.valueOf(listaEstado.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listaId_detalle.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView id_det, id_resumen, id_proyecto, fecha_inicio, fecha_final, horas_asignadas, monto, benef_indir, benef_dir, estado_det;
        LinearLayout itemsDetalleResumen;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            id_det = itemView.findViewById(R.id.txtId_det_res);
            id_resumen = itemView.findViewById(R.id.txtId_resumen);
            id_proyecto = itemView.findViewById(R.id.txtId_proyecto);
            fecha_final = itemView.findViewById(R.id.txtFechafinal);
            fecha_inicio = itemView.findViewById(R.id.txtFechaInicio);
            horas_asignadas =itemView.findViewById(R.id.txtHoras_asignadas);
            monto = itemView.findViewById(R.id.txtMonto);
            benef_indir = itemView.findViewById(R.id.txtBenef_indir);
            benef_dir = itemView.findViewById(R.id.txtBenef_dir);
            estado_det = itemView.findViewById(R.id.txtEstadoDet);
            itemsDetalleResumen = itemView.findViewById(R.id.LayoutDetalleResumenSocial);
        }
    }
}
