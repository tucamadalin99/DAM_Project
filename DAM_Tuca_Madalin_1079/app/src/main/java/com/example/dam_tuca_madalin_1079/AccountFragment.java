package com.example.dam_tuca_madalin_1079;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AccountFragment extends Fragment {

    private Button btnChangePass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
       btnChangePass = view.findViewById(R.id.btnChangePass);
       btnChangePass.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                PasswordDialog passDialog = new PasswordDialog();
                passDialog.show(getFragmentManager(), "PasswordDialog");
           }
       });

       return view;
    }


}