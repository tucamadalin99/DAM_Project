package com.example.dam_tuca_madalin_1079;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.room.Room;

import com.muddzdev.styleabletoast.StyleableToast;

public class PasswordDialog extends AppCompatDialogFragment {
    //widgets
    AppDb db;
    private EditText etNewPass;
    private TextView mActionOk, mActionCancel;
    Globals globals;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_pass_dialog, container, false);
        mActionOk = view.findViewById(R.id.actionOk);
        mActionCancel = view.findViewById(R.id.actionCancel);
        etNewPass = view.findViewById(R.id.etNewPass);
        db = Room.databaseBuilder(getContext(), AppDb.class, "users").allowMainThreadQueries().build();
        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globals = new Globals(getContext());
                db.userDAO().updatePassword(etNewPass.getText().toString(), globals.returnUserSession());
                StyleableToast.makeText(getContext(), "Succesfully updated password!", Toast.LENGTH_LONG, R.style.successToast).show();
            }
        });

        mActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });


        return view;
    }

}
