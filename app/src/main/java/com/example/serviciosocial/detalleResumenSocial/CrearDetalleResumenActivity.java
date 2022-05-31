package com.example.serviciosocial.detalleResumenSocial;

import androidx.appcompat.app.AppCompatActivity;
import com.example.serviciosocial.R;
import com.example.serviciosocial.proyecto.ControlProyecto;
import com.example.serviciosocial.proyecto.Proyecto;
import com.example.serviciosocial.resumensocial.ConsultarResumenActivity;
import com.example.serviciosocial.resumensocial.ControlResumenSocial;
import com.example.serviciosocial.resumensocial.Resumensocial;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class CrearDetalleResumenActivity extends AppCompatActivity {

    ControlDetalleResumen helper;
    ControlResumenSocial helperR;
    ControlProyecto helperP;
    Spinner spinerResumen, spinerProyecto;
    ArrayList<String> id_resumen, id_proyecto;
    String id_r, id_p;

    EditText txtId, txtFechaI, txtFechaF, txtHoras, txtMonto, txtBDir, txtBInd, txtEstado;

    Button btnGuardar;
    boolean camp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_detalle_resumen);

        helper = new ControlDetalleResumen(this);
        helperP = new ControlProyecto(this);
        helperR = new ControlResumenSocial(this);
        spinerProyecto = (Spinner) findViewById(R.id.spinnerIdproyecto);
        spinerResumen = (Spinner) findViewById(R.id.spinnerIdResumen);

        txtId = (EditText) findViewById(R.id.txtId_det_res);
        txtFechaI = (EditText) findViewById(R.id.txtFechainicio);
        txtFechaF = (EditText) findViewById(R.id.txtFechafinal);
        txtHoras = (EditText) findViewById(R.id.txtHorasasignadas);
        txtMonto = (EditText) findViewById(R.id.txtMontoDetalle);
        txtBInd = (EditText) findViewById(R.id.txtBenef_indir);
        txtBDir = (EditText) findViewById(R.id.txtBenef_dir);
        txtEstado = (EditText) findViewById(R.id.txtEstadoDet);
        btnGuardar = (Button) findViewById(R.id.btnGuardarCarrera);

        id_resumen = new ArrayList();
        id_proyecto = new ArrayList();

        id_resumen.add("ID resumen servicio social");
        id_proyecto.add("ID proyecto");
        //Detalle de resument social
        helper.abrir();
        ArrayList<DetalleResumenSocial> itemsSpinnerD = helper.consultarDetalleResumen();
        helper.cerrar();
        //Resumen servicio social
        helperR.abrir();
        ArrayList<Resumensocial> itemsSpinnerR = helperR.consultarResumen();
        helperR.cerrar();
        //Proyecto
        helperP.abrir();
        //ArrayList<Proyecto> itemsSpinnerP = helperP.consultarModalidad();
        helperP.cerrar();
        Cursor cursor = helper.leerTodoDetalle();
        if(cursor.getCount()==0){

        }else{
            DetalleResumenSocial a;
            Iterator<DetalleResumenSocial> it = itemsSpinnerD.iterator();
            while(it.hasNext()){
                a = it.next();
                id_proyecto.add(String.valueOf(a.getId_proyecto()));
            }
        }
        Cursor cursorR = helperR.leerTodoResumen();
        if(cursorR.getCount()==0){

        }else{
            Resumensocial b;
            Iterator<Resumensocial> it = itemsSpinnerR.iterator();
            while(it.hasNext()){
                b = it.next();
                id_resumen.add(String.valueOf(b.getId_resumen()));
            }
        }
        ArrayAdapter<CharSequence> adaptadorP = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, id_proyecto);
        spinerProyecto.setAdapter(adaptadorP);

        ArrayAdapter<CharSequence> adaptadorR = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, id_resumen);
        spinerResumen.setAdapter(adaptadorR);

        spinerResumen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    id_r = id_resumen.get(i - 1);
                    camp = false;
                } else {
                    camp = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinerProyecto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    id_p = id_proyecto.get(i - 1);
                    camp = false;
                } else {
                    camp = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void guardarDetalleResumen(View v){
        if(verificarCamposLlenos(camp)){
            String id = txtId.getText().toString();
            String id_resumen = id_r;
            String id_proyecto = id_p;
            String fechaI = txtFechaI.getText().toString();
            String fechaF = txtFechaF.getText().toString();
            String horas = txtHoras.getText().toString();
            String monto = txtMonto.getText().toString();
            String bI = txtBInd.getText().toString();
            String bD = txtBDir.getText().toString();
            String estado = txtEstado.getText().toString();
            String regInsertados;

            DetalleResumenSocial d = new DetalleResumenSocial();
            d.setId_det_res(Integer.parseInt(id));
            d.setId_resumen(Integer.parseInt(id_resumen));
            d.setId_proyecto(Integer.parseInt(id_proyecto));
            d.setFecha_inicio(fechaI);
            d.setFecha_final(fechaF);
            d.setHoras_asignadas(Float.parseFloat(horas));
            d.setMonto(Float.parseFloat(monto));
            d.setBenef_indir(Integer.parseInt(bI));
            d.setBenef_dir(Integer.parseInt(bD));
            d.setEstado_det(estado);

            helper.abrir();
            regInsertados = helper.insertar(d);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ConsultarDetalleResumenActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_LONG).show();
        }
    }
    public boolean verificarCamposLlenos(boolean camp){
        if(txtId.getText().toString().isEmpty() || txtId.getText().toString() == null){
            return false;
        }else if(txtFechaF.getText().toString().isEmpty() || txtFechaF.getText().toString()==null){
            return false;
        }else if(txtFechaI.getText().toString().isEmpty() || txtFechaI.getText().toString() == null){
            return false;
        }else if(txtHoras.getText().toString().isEmpty() || txtHoras.getText().toString() == null){
            return false;
        }else if(txtMonto.getText().toString().isEmpty() || txtMonto.getText().toString()==null){
            return false;
        }else if(txtBInd.getText().toString().isEmpty() || txtBInd.getText().toString() == null){
            return false;
        }else if(txtBDir.getText().toString().isEmpty() || txtBDir.getText().toString() == null){
            return false;
        }else if(txtEstado.getText().toString().isEmpty() || txtEstado.getText().toString() == null){
            return false;
        }else if(camp){
            return false;
        }else{
            return true;
        }
    }
}