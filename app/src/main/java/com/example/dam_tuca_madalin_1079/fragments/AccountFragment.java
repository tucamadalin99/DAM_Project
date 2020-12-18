package com.example.dam_tuca_madalin_1079.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dam_tuca_madalin_1079.AppDb;
import com.example.dam_tuca_madalin_1079.Globals;
import com.example.dam_tuca_madalin_1079.PasswordDialog;
import com.example.dam_tuca_madalin_1079.R;
import com.example.dam_tuca_madalin_1079.classes.User;
import com.muddzdev.styleabletoast.StyleableToast;

public class AccountFragment extends Fragment {

    private Button btnChangePass;
    private EditText etName;
    private EditText etSurname;
    private EditText etEmail;
    private PasswordDialog passDialog;
    private Button btnUpdateData;
    private AppDb db;
    Globals globals;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        db = Room.databaseBuilder(getContext(), AppDb.class, "users").allowMainThreadQueries().build();
        globals = new Globals(getContext());
        AsyncTask.execute(()->{
            User currentUser = db.userDAO().getUserById(globals.returnUserSession());
            //String testVal = bundle.getString("name");
            etName = view.findViewById(R.id.etEdName);
            etSurname = view.findViewById(R.id.etEdSurname);
            etEmail = view.findViewById(R.id.etEdEmail);
            btnUpdateData = view.findViewById(R.id.btnSubEdit);
            btnChangePass = view.findViewById(R.id.btnChangePass);
            String name = getArguments().getString("name");
            String surname = getArguments().getString("surname");
            String email = getArguments().getString("email");
            etName.setText(name);
            etSurname.setText(surname);
            etEmail.setText(email);
            etName.setText(currentUser.getName());
            etSurname.setText(currentUser.getSurname());
            etEmail.setText(currentUser.getEmail());

            btnChangePass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    passDialog = new PasswordDialog();
                    passDialog.show(getFragmentManager(), "PasswordDialog");
                }
            });

        });

        AsyncTask.execute(()->{
            btnUpdateData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.userDAO().updateUserData(etName.getText().toString(),etSurname.getText().toString(),etEmail.getText().toString(), globals.returnUserSession());
                    StyleableToast.makeText(getContext(), "Succesfully updated your data!", Toast.LENGTH_LONG, R.style.successToast).show();

                }
            });
        });

       return view;
    }


}