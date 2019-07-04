package com.optimizingknowledge.ok;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


import de.hdodenhof.circleimageview.CircleImageView;

public class SetupActivity extends AppCompatActivity
{
    private EditText IdNumber, FullName, ContactNumber;
    private Button SaveInformationbuttion;
    private CircleImageView ProfileImage;

    String currentUserID;
    final static int Gallery_Pick = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);


        IdNumber = (EditText) findViewById(R.id.setup_id_number);
        FullName = (EditText) findViewById(R.id.setup_full_name);
        ContactNumber = (EditText) findViewById(R.id.setup_contact_number);
        SaveInformationbuttion = (Button) findViewById(R.id.setup_information_button);
        ProfileImage = (CircleImageView) findViewById(R.id.setup_profile_image);

        };



}