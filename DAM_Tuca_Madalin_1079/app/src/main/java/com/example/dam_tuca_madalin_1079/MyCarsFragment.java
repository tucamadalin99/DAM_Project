package com.example.dam_tuca_madalin_1079;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyCarsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyCarsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ExpandableListView expandableListView;
    TextView tvHeader;
    List<String> listGroup;
    HashMap<String, List<String>> listItem;
    ExpandLvAdapater adapter;
    AppDb db;
    private Globals globals;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyCarsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyCarsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyCarsFragment newInstance(String param1, String param2) {
        MyCarsFragment fragment = new MyCarsFragment();
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
        View view = inflater.inflate(R.layout.fragment_my_cars, container, false);
        globals = new Globals(getContext());
        tvHeader = view.findViewById(R.id.emptyHeader);
        expandableListView = view.findViewById(R.id.expandable_list_view);
        listGroup = new ArrayList<>();
        listItem = new HashMap<>();
        adapter = new ExpandLvAdapater(getContext(), listGroup,listItem);
        expandableListView.setAdapter(adapter);
        setListViewHeight(expandableListView);
        db = Room.databaseBuilder(getContext(), AppDb.class, "users").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        initListData();

        return view;
    }

    private void initListData(){
        UserWithCars user = db.userDAO().getUserCars(globals.returnUserSession());
        if(user.cars.size() > 0) {

            for (Car car : user.cars) {
                listGroup.add(car.getBrand() + " " + car.getModel());
            }
            Log.d("TOTAL CARS", listGroup.toString());

            List<String> listOfProps = new ArrayList<>();
            for (Car car : user.cars) {
                listOfProps.add("Year: " + car.getProducedYear());
                listOfProps.add(car.getCarBodyType());                listOfProps.add("Fuel: " + car.getFuelType());
                listOfProps.add("Capacity: " + car.getCilindricCapacity() + "cm^3");
            }

            listItem.put(listGroup.get(0), listOfProps.subList(0, 4));
            int counter = 1;
            int j = 8;
            if (user.cars.size() > 1) {
                for (int i = 4; i < listOfProps.size(); i += 4) {
                    listItem.put(listGroup.get(counter), listOfProps.subList(i, j));
                    Log.d("LIST OF CARS", listOfProps.subList(i, j).toString());
                    j += 4;
                    counter++;
                }
            }
            adapter.notifyDataSetChanged();
        }else{
            //StyleableToast.makeText(getContext(), "You currently don't have any cars added!", Toast.LENGTH_LONG, R.style.errToast).show();
            tvHeader.setText("NO CARS ADDED IN YOUR ACCOUNT");
        }
    }

    //FIX THIS SHITTY FUNCTION
    private void setListViewHeight(ExpandableListView listView) {
        ExpandLvAdapater listAdapter = (ExpandLvAdapater) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)))
                    || ((!listView.isGroupExpanded(i)))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

                    totalHeight += listItem.getMeasuredHeight();

                }
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 2000;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}