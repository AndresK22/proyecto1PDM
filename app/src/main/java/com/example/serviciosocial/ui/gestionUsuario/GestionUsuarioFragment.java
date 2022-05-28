package com.example.serviciosocial.ui.gestionUsuario;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.serviciosocial.AgregarUsuarioActivity;
import com.example.serviciosocial.ControlLogin;
import com.example.serviciosocial.CustomAdapterUsuario;
import com.example.serviciosocial.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GestionUsuarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GestionUsuarioFragment extends Fragment {

    FloatingActionButton buttonAgregarUsuario;
    ControlLogin controlLogin;
    ArrayList<String> id_usuario,nombre_usuario,contra_usuario,rol_usuario;
    CustomAdapterUsuario customAdapterUsuario;
    RecyclerView recyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public GestionUsuarioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GestionUsuarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GestionUsuarioFragment newInstance(String param1, String param2) {
        GestionUsuarioFragment fragment = new GestionUsuarioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_gestion_usuario, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        controlLogin = new ControlLogin(view.getContext());
        id_usuario = new ArrayList<>();
        nombre_usuario = new ArrayList<>();
        contra_usuario = new ArrayList<>();
        rol_usuario = new ArrayList<>();

        Cursor cursor = controlLogin.consultarUsuarios();

        while (cursor.moveToNext()){
            id_usuario.add(Integer.toString(cursor.getInt(0)));
            nombre_usuario.add(cursor.getString(1));
            contra_usuario.add(cursor.getString(2));
            rol_usuario.add(controlLogin.getRolusuario(cursor.getInt(0)));
        }

        customAdapterUsuario = new CustomAdapterUsuario(getActivity(),view.getContext(),id_usuario,nombre_usuario,contra_usuario,rol_usuario);
        recyclerView.setAdapter(customAdapterUsuario);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));



        buttonAgregarUsuario = (FloatingActionButton) view.findViewById(R.id.fab);
        buttonAgregarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AgregarUsuarioActivity.class));
            }
        });

        return view;
    }

}