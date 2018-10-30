package com.rp.myapplication;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_button);

        Button manageEmergencyContactsButton = (Button) findViewById(R.id.manageEmergencyContacts);
        Button helpButton = (Button) findViewById(R.id.helpButton);
        helpButton.setOnClickListener(this);
        manageEmergencyContactsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.manageEmergencyContacts)
        {
            startActivity(new Intent(MainActivity.this, ManageEmergencyContactsActivity.class));
            finish();
        }
        else if(view.getId() == R.id.helpButton){
            ((ManageEmergencyContactsActivity)mContext).sendSms();
        }
    }
}