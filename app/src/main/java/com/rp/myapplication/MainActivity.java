package com.rp.myapplication;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rp.myapplication.model.Contact;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String EMERGENCY_CONTACTS_LIST = "emergencyContactsList";
    private final int MY_PERMISSIONS_REQUEST_SEND_SMS = 3;
    private final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 4;
    private final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 5;
    private final int MY_PERMISSIONS_REQUEST_COARSE_LOCATION = 6;
    private FirebaseAuth mAuth;
    private ArrayList<Contact> emergencyContactsArrayList;
    private SharedPreferences sharedPref;
    private LocationManager locationManager;
    private SharedPreferences.Editor editor;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_button);

        mAuth = FirebaseAuth.getInstance();
        sharedPref = this.getSharedPreferences(EMERGENCY_CONTACTS_LIST, Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                        }
                    }
                });

        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        }

        Button manageEmergencyContactsButton = (Button) findViewById(R.id.manageEmergencyContacts);
        Button helpButton = (Button) findViewById(R.id.helpButton);
        helpButton.setOnClickListener(this);
        manageEmergencyContactsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.manageEmergencyContacts) {
            startActivity(new Intent(MainActivity.this, ManageEmergencyContactsActivity.class));
            finish();
        } else if (view.getId() == R.id.helpButton) {
            sendSms();
        }
    }

    public void getArrayList() {
        Gson gson = new Gson();
        String json = sharedPref.getString(EMERGENCY_CONTACTS_LIST, null);

        Type type = new TypeToken<ArrayList<Contact>>() {
        }.getType();
        if (gson.fromJson(json, type) == null) {
            emergencyContactsArrayList = new ArrayList<Contact>();
        } else {
            emergencyContactsArrayList = gson.fromJson(json, type);
        }
    }

    public void sendSms() {
        getArrayList();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_COARSE_LOCATION);
        }
        SmsManager smsManager = SmsManager.getDefault();
        try {
            Location currentGPSLocation = locationManager.getLastKnownLocation("gps");
            Location currentAGPSLocation = locationManager.getLastKnownLocation("network");
            Location currentWIFILocation = locationManager.getLastKnownLocation("passive");
            StringBuilder message = new StringBuilder("Preciso de ajuda! http://maps.google.com/maps?saddr=");

            for (Contact contact : emergencyContactsArrayList) {
                if(currentGPSLocation != null) {
                    message.append(currentGPSLocation.getLatitude());
                    message.append(",");
                    message.append(currentGPSLocation.getLongitude());
                    smsManager.sendTextMessage(contact.getPhoneNumber(), null, message.toString(), null, null);
                }
                else if(currentAGPSLocation != null){
                    message.append(currentAGPSLocation.getLatitude());
                    message.append(",");
                    message.append(currentAGPSLocation.getLongitude());
                    smsManager.sendTextMessage(contact.getPhoneNumber(), null, message.toString(), null, null);
                }
                else if(currentWIFILocation != null){
                    message.append(currentWIFILocation.getLatitude());
                    message.append(",");
                    message.append(currentWIFILocation.getLongitude());
                    smsManager.sendTextMessage(contact.getPhoneNumber(), null, message.toString(), null, null);
                }
                else {
                    smsManager.sendTextMessage(contact.getPhoneNumber(), null, "Preciso de ajuda! Não consigo mandar minha localização!", null, null);
                }
            }
            if( currentAGPSLocation != null && currentGPSLocation != null && currentWIFILocation != null)
                Toast.makeText(getApplicationContext(), "SMS's enviados sem localização!", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "SMS's enviados!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            if (e.toString().contains(Manifest.permission.READ_PHONE_STATE) && ContextCompat
                    .checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
                        .READ_PHONE_STATE}, MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Habilite a permissão de SMS para o aplicativo nas configurações do celular.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            case MY_PERMISSIONS_REQUEST_COARSE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Habilite a permissão de Localização para o aplicativo nas configurações do celular.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            case MY_PERMISSIONS_REQUEST_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Habilite a permissão de Localização para o aplicativo nas configurações do celular.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }

}