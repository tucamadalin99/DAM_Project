package com.example.dam_tuca_madalin_1079.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dam_tuca_madalin_1079.R;
import com.example.dam_tuca_madalin_1079.classes.GasStation;

import java.util.ArrayList;

public class GasListAdapter extends ArrayAdapter<GasStation> {

    private static final String TAG = "GasListAdapter";

    private Context mContext;
    int mResource;

    public GasListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<GasStation> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int imgId = getItem(position).getImgId();
        String name = getItem(position).getName();
        Float petrol = getItem(position).getPetromPrice();
        Float diesel = getItem(position).getDieselPrice();

        //Objects
        GasStation station = new GasStation(imgId,name,petrol,diesel);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        ImageView imgName = (ImageView) convertView.findViewById(R.id.imgGasBrand);
        TextView tvPetrol = (TextView) convertView.findViewById(R.id.tvGasPrice);
        TextView tvDiesel = (TextView) convertView.findViewById(R.id.tvDieselPrice);

        imgName.setImageResource(imgId);
        tvPetrol.setText(petrol.toString() + " RON");
        tvDiesel.setText(diesel.toString() + " RON");

        return convertView;
    }
}
