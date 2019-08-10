package com.optimizingknowledge.ok;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;



import com.google.android.material.navigation.NavigationView;


import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private RecyclerView postList;
    private Toolbar mToolbar;
    private FirebaseAuth mAuth;
    private DatabaseReference UsersRef;
    private CardView lecturesCardId, schedulesCardId, attendanceCardId, progressCardId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lecturesCardId = findViewById(R.id.lecturessId);
        schedulesCardId = findViewById(R.id.schedulesId);
        attendanceCardId  = findViewById(R.id.attendanceId);
        progressCardId = findViewById(R.id.progressId);


        mAuth = FirebaseAuth.getInstance();
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");

        mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Home");


        drawerLayout = (DrawerLayout) findViewById(R.id.drawable_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View navView = navigationView.inflateHeaderView(R.layout.navigation_header);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                UserMenuSelector(item);
                return false;
            }
        });

        lecturesCardId.setOnClickListener(this);
        schedulesCardId.setOnClickListener(this);
        attendanceCardId.setOnClickListener(this);
        progressCardId.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null)
        {
            SendUserToLoginActivity();
        }
        else
        {
            CheckUserExistence();
        }
    }


    private void CheckUserExistence()
    {
        final String current_user_id = mAuth.getCurrentUser().getUid();

        UsersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if(!dataSnapshot.hasChild(current_user_id))
                {
                    SendUserToSetupActivity();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void SendUserToSetupActivity() {
        Intent setupIntent = new Intent(MainActivity.this, SetupActivity.class);
        setupIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(setupIntent);
        finish();
    }


    private void SendUserToLoginActivity()
    {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void UserMenuSelector(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.nav_home:
                Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_lectures:
                Toast.makeText(getApplicationContext(),"Lectures",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_schedules:
                Toast.makeText(getApplicationContext(),"Schedules",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_attendance:
                Toast.makeText(getApplicationContext(),"Attendance",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_progress:
                Toast.makeText(getApplicationContext(),"Progress",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_settings:
                Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_logout:
                mAuth.signOut();
                SendUserToLoginActivity();
                break;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.lecturessId:
            {
                Intent lectureIntent = new Intent(MainActivity.this, LecturesActivity.class);
                startActivity(lectureIntent);
                break;
            }

            case R.id.schedulesId:
            {
                Intent schedulesIntent = new Intent(MainActivity.this, SchedulesActivity.class);
                startActivity(schedulesIntent);
                break;
            }

            case R.id.attendanceId:
            {
                Intent attendanceIntent = new Intent(MainActivity.this, AttendanceActivity.class);
                startActivity(attendanceIntent);
                break;
            }

            case R.id.progressId:
            {
                Intent progressIntent = new Intent(MainActivity.this, ProgressActivity.class);
                startActivity(progressIntent);
                break;
            }
        }
    }
}
