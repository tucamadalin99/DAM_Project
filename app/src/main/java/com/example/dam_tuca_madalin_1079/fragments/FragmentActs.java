package com.example.dam_tuca_madalin_1079.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dam_tuca_madalin_1079.adapters.ActsAdapter;
import com.example.dam_tuca_madalin_1079.AppDb;
import com.example.dam_tuca_madalin_1079.Globals;
import com.example.dam_tuca_madalin_1079.R;
import com.example.dam_tuca_madalin_1079.classes.Act;
import com.muddzdev.styleabletoast.StyleableToast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentActs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentActs extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView lvActs;
    private Globals globals;
    AppDb db;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView emptyHeader;

    public FragmentActs() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentActs.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentActs newInstance(String param1, String param2) {
        FragmentActs fragment = new FragmentActs();
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
        View view = inflater.inflate(R.layout.fragment_acts, container, false);
        lvActs = view.findViewById(R.id.list_view_acts);
        emptyHeader = view.findViewById(R.id.headerEmptyActs);
        db = Room.databaseBuilder(getContext(), AppDb.class, "users").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        globals = new Globals(getContext());
        List<Act> userActs = db.actDAO().getUserActs(globals.returnUserSession());
        ActsAdapter adapter = new ActsAdapter(getContext(), R.layout.adapter_acts_layout, (ArrayList)userActs);;
        if(userActs.size() > 0){
            emptyHeader.setText("");
            lvActs.setAdapter(adapter);
            globals.setListViewHeightBasedOnChildren(lvActs);
        }

        lvActs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(getContext());
                adb.setTitle("Delete");
                adb.setMessage("This will delete the selected item");
                final int posToRemove = position;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Act deleteAct = userActs.get(position);
                        userActs.remove(position);
                        adapter.notifyDataSetChanged();
                       AsyncTask.execute(()->{
                           db.actDAO().deleteAct(deleteAct);
                       });
                        StyleableToast.makeText(getContext(), "Act deleted from your account", Toast.LENGTH_LONG, R.style.successToast).show();
                    }});
                adb.show();
            }
        });


        return view;
    }
}