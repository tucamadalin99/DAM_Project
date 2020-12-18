package com.example.dam_tuca_madalin_1079.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dam_tuca_madalin_1079.R;
import com.example.dam_tuca_madalin_1079.classes.Act;

import java.util.ArrayList;

public class ActsAdapter extends ArrayAdapter<Act> {
    private static final String TAG = "ActsListAdapter";
    private Context mContext;
    int mResource;

    public ActsAdapter(Context context, int resource, ArrayList<Act> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
         String type = getItem(position).getType();
         String car = getItem(position).getBrand() + " " + getItem(position).getModel();
         String date = getItem(position).getStartDate() + " " + getItem(position).getEndDate();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        TextView tvType = (TextView) convertView.findViewById(R.id.tvType);
        TextView tvCar = (TextView) convertView.findViewById(R.id.tvCar);
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);

        tvType.setText(type);
        tvCar.setText(car);
        tvDate.setText(date);

        return convertView;
    }
}
