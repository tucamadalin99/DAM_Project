package com.example.dam_tuca_madalin_1079.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.dam_tuca_madalin_1079.R;
import com.example.dam_tuca_madalin_1079.activties.ActsActivity;
import com.example.dam_tuca_madalin_1079.activties.ConsuamblesActivity;
import com.example.dam_tuca_madalin_1079.activties.LicenseActivity;
import com.example.dam_tuca_madalin_1079.activties.VehicleActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RelativeLayout btnAddVehicle;
    private RelativeLayout btnAddActs;
    private RelativeLayout btnCons;
    private RelativeLayout btnAddLicense;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_home.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
       initButtons(view);
       setupListeners();
        return view;
    }

    public void initButtons(View v){
        btnAddLicense = v.findViewById(R.id.bigBtnLicense);
        btnAddVehicle = v.findViewById(R.id.bigBtnVehicle);
        btnAddActs = v.findViewById(R.id.bigBtnActs);
        btnCons = v.findViewById(R.id.bigBtnCons);
    }

    public void setupListeners(){
        btnAddLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(LicenseActivity.class);
            }
        });

        btnAddVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(VehicleActivity.class);
            }
        });

        btnAddActs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(ActsActivity.class);
            }
        });

        btnCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(ConsuamblesActivity.class);
            }
        });
    }

    public void changeActivity(Class activity){
        Intent intent = new Intent(getActivity(), activity);
        startActivity(intent);
    }
}