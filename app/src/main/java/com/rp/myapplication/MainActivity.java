package com.rp.myapplication;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private FirebaseAuth mAuth;
    private ArrayList<Contact> emergencyContactsArrayList;
    private SharedPreferences sharedPref;
    private LocationManager locationManager;
    private SharedPreferences.Editor editor;

    private Location currentGPSLocation;
    private Location currentAGPSLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_button);
        mAuth = FirebaseAuth.getInstance();
        sharedPref = this.getSharedPreferences(EMERGENCY_CONTACTS_LIST, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        askPermissions();

        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            requestLocation();
        } catch (SecurityException e) {
            if
                    (e.toString().contains(Manifest.permission.ACCESS_FINE_LOCATION) && ContextCompat
                    .checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
                        .ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_FINE_LOCATION);
            }
        }

        mAuth.signInAnonymously();

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
        askPermissions();

        SmsManager smsManager = SmsManager.getDefault();
        try {

            StringBuilder message = new StringBuilder("Preciso de ajuda! ");

            if( emergencyContactsArrayList.size() == 0){
                Toast.makeText(getApplicationContext(), "Adicione contatos de emergência antes!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, ManageEmergencyContactsActivity.class);
                startActivity(intent);
                return;
            }

            if(currentGPSLocation != null && currentAGPSLocation != null) {
                message.append("http://maps.google.com/?q=");
                if(currentGPSLocation.getAccuracy() > currentAGPSLocation.getAccuracy()) {
                    message.append(currentGPSLocation.getLatitude());
                    message.append(",");
                    message.append(currentGPSLocation.getLongitude());
                } else {
                    message.append(currentGPSLocation.getLatitude());
                    message.append(",");
                    message.append(currentGPSLocation.getLongitude());
                }
            }
            else if (currentGPSLocation != null) {
                message.append("http://maps.google.com/?q=");
                message.append(currentGPSLocation.getLatitude());
                message.append(",");
                message.append(currentGPSLocation.getLongitude());
            }
            else if(currentAGPSLocation != null) {
                message.append("http://maps.google.com/?q=");
                message.append(currentAGPSLocation.getLatitude());
                message.append(",");
                message.append(currentAGPSLocation.getLongitude());
            } else {
                message.append("Mas Não consigo mandar minha localização!");
            }

            for (Contact contact : emergencyContactsArrayList) {
                smsManager.sendTextMessage(contact.getPhoneNumber(), null, message.toString(), null, null);
            }
            if( currentAGPSLocation == null && currentGPSLocation == null)
                Toast.makeText(getApplicationContext(), "SMS's enviados sem localização!", Toast.LENGTH_LONG).show();
            else {
                Toast.makeText(getApplicationContext(), "SMS's enviados!", Toast.LENGTH_LONG).show();
            }
        } catch (SecurityException | IllegalArgumentException  e) {
            if (e.toString().contains(Manifest.permission.READ_PHONE_STATE) && ContextCompat
                    .checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
                        .READ_PHONE_STATE}, MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
            } else if
                (e.toString().contains(Manifest.permission.ACCESS_FINE_LOCATION) && ContextCompat
                        .checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
                        .ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_FINE_LOCATION);
            }
        }
        stopRequestingLocation();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                return true;
            case R.id.oficial:
                Intent intent2 = new Intent(this, OficialInformation.class);
                startActivity(intent2);
                return true;
            case R.id.ong:
                Intent intent = new Intent(this, ONGInformation.class);
                startActivity(intent);
                return true;
            case R.id.psychologist:
                Intent intent3 = new Intent(this, PsychologistInformation.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void askPermissions(){
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
    }

    private void requestLocation() throws SecurityException{
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 0, 0, gpsListener);
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 0, 0, gpsListener);
    }

    private void stopRequestingLocation(){
        locationManager.removeUpdates(gpsListener);
    }

    private LocationListener gpsListener = new LocationListener()  {
        @Override
        public void onLocationChanged(Location location) throws SecurityException {
            currentGPSLocation = locationManager.getLastKnownLocation("gps");
            currentAGPSLocation = locationManager.getLastKnownLocation("network");
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {        }

        @Override
        public void onProviderEnabled(String provider) {        }

        @Override
        public void onProviderDisabled(String provider) {        }
    };

}