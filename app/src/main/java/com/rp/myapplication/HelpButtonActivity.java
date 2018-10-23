package com.rp.myapplication;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelpButtonActivity extends AppCompatActivity implements View.OnClickListener{

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_button);

        Button manageEmergencyContactsButton = (Button) findViewById(R.id.manageEmergencyContacts);
        manageEmergencyContactsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.manageEmergencyContacts)
        {
            startActivity(new Intent(HelpButtonActivity.this, ManageEmergencyContactsActivity.class));
            finish();
        }
        else if(v.getId() == R.id.helpButton){
            if(mContext instanceof ManageEmergencyContactsActivity){
                System.out.println("SendingSMS");
                ((ManageEmergencyContactsActivity)mContext).sendSms();
            }
        }
    }
}
