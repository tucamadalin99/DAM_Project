package com.example.dam_tuca_madalin_1079;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LicenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LicenseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    AppDb db;
    Globals globals;
    private TextView tvFullName;
    private TextView tvBorn;
    private TextView tvCity;
    private TextView tvCounty;
    private TextView tvIssueDate;
    private TextView tvEndDate;
    private TextView tvPolice;
    private TextView tvLicenseId;
    private TextView tvPersonId;
    private TextView tvTypes;

    public LicenseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LicenseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LicenseFragment newInstance(String param1, String param2) {
        LicenseFragment fragment = new LicenseFragment();
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
        View view =  inflater.inflate(R.layout.fragment_license, container, false);
        globals = new Globals(getContext());
        db = Room.databaseBuilder(getContext(), AppDb.class, "users").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        User currentUser = db.userDAO().getUserById(globals.returnUserSession());
        DriverLicense license = db.userDAO().getDriverLicense(globals.returnUserSession());
        if(license != null){
            initViews(view);
            tvFullName.setText(currentUser.getName() + " " + currentUser.getSurname());
            tvBorn.setText(currentUser.getBirthDate());
            tvCity.setText(currentUser.getCity());
            tvCounty.setText(currentUser.getCounty());
            tvIssueDate.setText("Issued: " + license.getIssueDate());
            tvEndDate.setText("Ends: " + license.getEndDate());
            tvLicenseId.setText("License: " + license.getLicenseId());
            tvPersonId.setText("Person: " + license.getPersonalId());
          //  tvTypes.setText(license.getType()); BUG TO SOLVE HERE NULL REF
        }else
            StyleableToast.makeText(getContext(), "You haven't added your license yet", Toast.LENGTH_LONG, R.style.errToast).show();

        return view;
    }

    private void initViews(View v){
        tvFullName = v.findViewById(R.id.tvNameSurname);
        tvBorn = v.findViewById(R.id.tvBorn);
        tvCity = v.findViewById(R.id.tvCity);
        tvCounty = v.findViewById(R.id.tvCounty);
        tvIssueDate = v.findViewById(R.id.tvIssueDate);
        tvEndDate = v.findViewById(R.id.tvEndDate);
        tvPolice = v.findViewById(R.id.tvPoliceStation);
        tvLicenseId = v.findViewById(R.id.tvLicenseId);
        tvPersonId = v.findViewById(R.id.tvPersonId);
        tvTypes = v.findViewById(R.id.tvLicenseType);
    }
}