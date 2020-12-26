package com.example.dam_tuca_madalin_1079.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dam_tuca_madalin_1079.AppDb;
import com.example.dam_tuca_madalin_1079.Globals;
import com.example.dam_tuca_madalin_1079.R;
import com.example.dam_tuca_madalin_1079.activties.ActsActivity;
import com.example.dam_tuca_madalin_1079.activties.ConsuamblesActivity;
import com.example.dam_tuca_madalin_1079.activties.LicenseActivity;
import com.example.dam_tuca_madalin_1079.activties.VehicleActivity;
import com.example.dam_tuca_madalin_1079.classes.DriverLicense;

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
    private ProgressBar mProgBar;
    private TextView tvLoading;
    private TextView tvUserHeader;
    private String uName;
    private String uSurname;
    Globals globals;
    AppDb db;
    private int mProgStatus = 0;
    private int limit = 0;
    private Handler handler = new Handler();

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
        globals = new Globals(getContext());
        db = Room.databaseBuilder(getContext(), AppDb.class, "users").fallbackToDestructiveMigration().allowMainThreadQueries().build();
       initButtons(view);
       mProgBar = (ProgressBar)view.findViewById(R.id.progressBar);
       tvLoading = (TextView)view.findViewById(R.id.tvLoadStatus);
       setupListeners();
       tvLoading.setText("Add license,car and acts to configure account");
       limit = 0;
       mProgBar.setProgress(0);
       try{
           uName = getArguments().getString("nameKey");
           uSurname = getArguments().getString("surnameKey");
       }catch(Error err){
           throw err;
        }

       if(uName == null && uSurname == null){
           tvUserHeader.setText("");
       }else
       tvUserHeader.setText("Welcome, " + uName + " " + uSurname);


       new Thread(new Runnable() {
           @Override
           public void run() {
               DriverLicense licenseCheck = db.userDAO().getDriverLicense(globals.returnUserSession());
               int carsCheck = db.carDAO().getCarCount(globals.returnUserSession());
               int actsCheck = db.actDAO().getActsCount(globals.returnUserSession());
               if(licenseCheck != null){
                   limit+=33;
                   tvLoading.setText("Account " + limit + "% Configured");
               }
               if(carsCheck > 0){
                   limit += 33;
                   tvLoading.setText("Account " + limit + "% Configured");
               }

               if(actsCheck > 0){
                   limit += 34;
                   tvLoading.setText("Account " + limit + "% Configured");
               }
               if(limit == 100){
                   tvLoading.setText("Your account is configured!");
               }
               while (mProgStatus < limit){
                   mProgStatus++;
                   android.os.SystemClock.sleep(5);
                   handler.post(new Runnable() {
                       @Override
                       public void run() {
                           mProgBar.setProgress(mProgStatus);
                       }
                   });
               }
               handler.post(new Runnable() {
                   @Override
                   public void run() {
                       tvLoading.setVisibility(View.VISIBLE);

                   }
               });
           }
       }).start();

        return view;
    }

    public void initButtons(View v){
        btnAddLicense = v.findViewById(R.id.bigBtnLicense);
        btnAddVehicle = v.findViewById(R.id.bigBtnVehicle);
        btnAddActs = v.findViewById(R.id.bigBtnActs);
        btnCons = v.findViewById(R.id.bigBtnCons);
        tvUserHeader = v.findViewById(R.id.userHeader);
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
        intent.putExtra("nameKey", uName);
        intent.putExtra("surnameKey", uSurname);
        startActivity(intent);
    }
}