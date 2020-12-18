package com.example.dam_tuca_madalin_1079.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.dam_tuca_madalin_1079.adapters.GasListAdapter;
import com.example.dam_tuca_madalin_1079.Globals;
import com.example.dam_tuca_madalin_1079.R;
import com.example.dam_tuca_madalin_1079.classes.GasStation;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView lvGas;
    Globals globals;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GasFragment newInstance(String param1, String param2) {
        GasFragment fragment = new GasFragment();
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
        View view = inflater.inflate(R.layout.fragment_gas, container, false);
        globals = new Globals(getContext());
        lvGas = (ListView) view.findViewById(R.id.list_view_gas);

        GasStation petrom = new GasStation(R.drawable.petrom,"Petrom", (float)4.32, (float)4.66);
        GasStation rompetrol = new GasStation(R.drawable.rompetrol,"Rompetrol", (float)4.55, (float)4.73);
        GasStation omv = new GasStation(R.drawable.omv,"OMV", (float)4.59, (float)4.76);
        GasStation mol = new GasStation(R.drawable.mol,"Mol", (float)4.57, (float)4.71);
        GasStation gazprom = new GasStation(R.drawable.gazprom,"Gazprom", (float)4.58, (float)4.72);
        GasStation lukoil = new GasStation(R.drawable.lukoil,"Lukoil", (float)4.56, (float)4.71);
        GasStation socar = new GasStation(R.drawable.socar,"Socar", (float)4.38, (float)4.69);

        ArrayList<GasStation> stationList = new ArrayList<>();
        stationList.add(petrom);
        stationList.add(rompetrol);
        stationList.add(omv);
        stationList.add(mol);
        stationList.add(lukoil);
        stationList.add(gazprom);
        stationList.add(socar);
        //Custom Adapter
        GasListAdapter adapter = new GasListAdapter(this.getContext(), R.layout.adapter_view_layout, stationList);
        lvGas.setAdapter(adapter);
       globals.setListViewHeightBasedOnChildren(lvGas); //Call anytime you modify the listview


        return view;
    }
}