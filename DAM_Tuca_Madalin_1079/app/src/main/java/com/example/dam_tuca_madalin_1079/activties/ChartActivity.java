package com.example.dam_tuca_madalin_1079.activties;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dam_tuca_madalin_1079.Globals;
import com.example.dam_tuca_madalin_1079.R;
import com.example.dam_tuca_madalin_1079.classes.Consumable;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.muddzdev.styleabletoast.StyleableToast;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartActivity extends AppCompatActivity {
    List<String> listOfTypes;
    List<Float> listOfPrices;
    private LinearLayout chartLayout;
    Globals globals;
    private int[] colors;
    private Button deleteChartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        chartLayout = findViewById(R.id.chartLayout);
        colors = new int[30];
        deleteChartBtn = findViewById(R.id.delChartBtn);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Consumables");
        readFromFirebaseDatabase();

        deleteChartBtn.setOnClickListener(v -> {
            ref.child("user" + globals.returnUserSession()).removeValue();
            StyleableToast.makeText(getApplicationContext(),"Chart deleted from account!", Toast.LENGTH_LONG, R.style.successToast).show();
        });

    }

    private float[] calculatePieChartData(List<Float> values){
        float total = 0;
        float[] pieVals = new float[values.size()];
        for(Float el : values){
            total+=el;
        }

        for(int i = 0; i < values.size(); i++){
            pieVals[i] = 360 * (values.get(i)/total);
        }

        return pieVals;
    }


    private void readFromFirebaseDatabase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        globals = new Globals(getApplicationContext());
        DatabaseReference ref = database.getReference("Consumables").child("user" +globals.returnUserSession());

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listOfTypes = new ArrayList<>();
                listOfPrices = new ArrayList<>();
                int i = 0;
                // HashMap<String, Object> consumables = (HashMap<String, Object> )(snapshot.getValue());
                for(DataSnapshot ds: snapshot.getChildren()){
                    listOfTypes.add(ds.child("type").getValue(String.class));
                    listOfPrices.add(ds.child("price").getValue(Float.class));
                        if(ds.child("type").getValue(String.class).compareTo("Oil") == 0)
                            colors[i] = Color.BLACK;
                        if(ds.child("type").getValue(String.class).compareTo("Coolant") == 0)
                            colors[i] = Color.RED;
                        if(ds.child("type").getValue(String.class).compareTo("Freon") == 0)
                            colors[i] = Color.BLUE;
                        if(ds.child("type").getValue(String.class).compareTo("Brake Fluid") == 0)
                            colors[i] = Color.YELLOW;
                        if(ds.child("type").getValue(String.class).compareTo("Brake Pads") == 0)
                            colors[i] = Color.GREEN;

                    i++;
                }
                //TODO
//
                chartLayout.addView(new PieChartView(getApplicationContext(), calculatePieChartData(listOfPrices)));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("firebase_read", "Failed to read values.", error.toException());
            }
        });
    }

    public class PieChartView extends View {
        private float[] valsDegree;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private RectF rectangle = new RectF(200,200,850,850);
        private int tmp = 0;
        public PieChartView(Context context, float[] values) {
            super(context);
            valsDegree = new float[values.length];
            for(int i = 0; i < values.length; i++){
                valsDegree[i] = values[i];
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            for(int i = 0; i < valsDegree.length; i++){
                paint.setColor(colors[i]);
                if(i == 0){
                    canvas.drawArc(rectangle,0,valsDegree[i], true,paint);
                }else{
                    tmp += (int)valsDegree[i - 1];
                    canvas.drawArc(rectangle,tmp,valsDegree[i], true,paint);
                }
            }
        }
    }

}