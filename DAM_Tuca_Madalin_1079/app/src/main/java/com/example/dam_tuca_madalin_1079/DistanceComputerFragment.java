package com.example.dam_tuca_madalin_1079;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DistanceComputerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DistanceComputerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText etDistance;
    private EditText etCons;
    private EditText etGasPrice;
    private Button computeBtn;
    private TextView result;
    private boolean valid = true;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DistanceComputerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DistanceComputerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DistanceComputerFragment newInstance(String param1, String param2) {
        DistanceComputerFragment fragment = new DistanceComputerFragment();
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
        View view = inflater.inflate(R.layout.fragment_distance_computer, container, false);
        initViews(view);
        computeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etDistance.getText().toString().isEmpty()  || etCons.getText().toString().isEmpty() || etGasPrice.toString().isEmpty()){
                    StyleableToast.makeText(getContext(),"Make sure you complete all the fields!", Toast.LENGTH_LONG, R.style.errToast).show();
                    valid = false;
                } else if(etDistance.getText().toString().matches("^[a-zA-Z]+$") || etCons.getText().toString().matches("^[a-zA-Z]+$") || etGasPrice.getText().toString().matches("^[a-zA-Z]+$")){
                    StyleableToast.makeText(getContext(),"Your fields should be only numeric!", Toast.LENGTH_LONG, R.style.errToast).show();
                    valid = false;
                }else{
                    valid = true;
                    int distance = Integer.parseInt(etDistance.getText().toString());
                    float consumption = Float.parseFloat(etCons.getText().toString());
                    float pricePerLiter = Float.parseFloat(etGasPrice.getText().toString());
                    String resultLiters = String.format("%.2f", computeLiters(distance,consumption));
                    String resultPrice = String.format("%.2f", computePrice(distance,consumption,pricePerLiter));
                    String resultStr = resultLiters + " liters, at " + resultPrice + " RON";
                        result.setText(resultStr);
                }
            }
        });
        return view;
    }

    private void initViews(View view){
        this.etDistance = view.findViewById(R.id.etInputDistance);
        this.etCons = view.findViewById(R.id.etInputConsumption);
        this.etGasPrice = view.findViewById(R.id.etInputGasPrice);
        this.computeBtn = view.findViewById(R.id.btnComputePrice);
        this.result = view.findViewById(R.id.tvResult);
    }

    private float computePrice(int distance, float consumption, float pricePerLiter){
        float litersPerKm = consumption/100;
        float literPerTotalDist = litersPerKm * distance;
        return literPerTotalDist * pricePerLiter;
    }
    private float computeLiters(int distance, float consumption){
        float litersPerKm = consumption/100;
        return litersPerKm * distance;
    }
}