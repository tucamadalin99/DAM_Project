package com.example.dam_tuca_madalin_1079.activties;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.dam_tuca_madalin_1079.fragments.AccountFragment;
import com.example.dam_tuca_madalin_1079.AppDb;
import com.example.dam_tuca_madalin_1079.fragments.DistanceComputerFragment;
import com.example.dam_tuca_madalin_1079.fragments.FragmentActs;
import com.example.dam_tuca_madalin_1079.fragments.GasFragment;
import com.example.dam_tuca_madalin_1079.fragments.HomeFragment;
import com.example.dam_tuca_madalin_1079.fragments.LicenseFragment;
import com.example.dam_tuca_madalin_1079.fragments.MyCarsFragment;
import com.example.dam_tuca_madalin_1079.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    String nameExtra;
    String surnameExtra;
    String emailExtra;
    AppDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeFragment(new HomeFragment());
//        Bundle extras = getIntent().getExtras();
//        nameExtra = extras.getString("nameKey");
//        surnameExtra = extras.getString("surnameKey");
//        emailExtra = extras.getString("emailKey");

        //Delete this after testing
        db = Room.databaseBuilder(this, AppDb.class, "users").fallbackToDestructiveMigration().allowMainThreadQueries().build();
//        List<UserWithCars> cars = db.userDAO().getUsersWithCars();
//        for(UserWithCars user: cars){
//            Log.d("Car###", user.cars.toString());
//        };
//        Log.d("CARS COUNT", "size: " + cars.size());

        //HOOKS
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

       //Nav drawer
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
       //toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
           // super.onBackPressed();
            changeFragment(new HomeFragment());
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (item.getItemId()){
            case R.id.nav_home:
                changeFragment(new HomeFragment());
                break;
            case R.id.nav_user:
                AccountFragment fragmentAcc = new AccountFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", nameExtra);
                bundle.putString("surname", surnameExtra);
                bundle.putString("email", emailExtra);
                fragmentAcc.setArguments(bundle);
                changeFragment(fragmentAcc);
                break;

            case R.id.nav_driver:
                changeFragment(new LicenseFragment());
                break;

            case R.id.nav_acts:
//                Globals globals = new Globals(getApplicationContext());
//                UserWithCars u = db.userDAO().getUserCars(globals.returnUserSession());
//                Log.d("TAG SHIT", u.acts.toString());
                changeFragment(new FragmentActs());
                break;

            case R.id.nav_gas:
                changeFragment(new GasFragment());
                break;

            case R.id.nav_dist:
                changeFragment(new DistanceComputerFragment());
                break;

            case R.id.nav_cars:
                Log.d("NAV CARS", "TEST");
                changeFragment(new MyCarsFragment());
                break;

            case R.id.nav_logout:
                changeActivity(Login.class);
                break;

        }
        return true;
    }

    private void changeFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_fragment, fragment);
        fragmentTransaction.commit();
    }

    public void changeActivity(Class activity){
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
}