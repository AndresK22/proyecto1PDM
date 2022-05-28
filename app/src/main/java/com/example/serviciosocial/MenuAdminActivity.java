package com.example.serviciosocial;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuAdminActivity extends ListActivity {

            String[] menu={"Gestionar categoria","Gestionar modalidad","Gestionar proyectos"};
            String[] carpeta ={"categoria","modalidad","proyecto"};
            String[] activities={"ConsultarCategoriaActivity","ConsultarModalidadActivity","ConsultarProyectoActivity"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, menu));

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);

        if(position!=3){

            String nombreValue=activities[position];
            String nombreCarpeta=carpeta[position];

            try{
                Class<?>
                        clase=Class.forName("com.example.serviciosocial."+ nombreCarpeta +"."+ nombreValue);
                Intent inte = new Intent(this,clase);
                this.startActivity(inte);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        else{

            //CODIGO PARA LLENAR BASE DE DATOS
        }
    }
}