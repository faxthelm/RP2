package com.rp.myapplication;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RegisterEmergencyContactsActivity extends AppCompatActivity {

    private final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private static final int PICK_CONTACT = 2;

    private ArrayList<String> contactIdList = new ArrayList<String>();

    private SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
    private SharedPreferences.Editor editor = sharedPref.edit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_emergency_contacts);

        contactIdList = getArrayList("contactIdList");
        loadContactsListView();

        //Checa se temos a permissão de acessar os contatos
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        }

         Button registerEmergencyContactButton = (Button) findViewById(R.id.registerEmergencyContactButton);
         ListView emergencyContactsList = (ListView) findViewById(R.id.emergencyContactsList);

         registerEmergencyContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver resolver = getContentResolver();
                resolver.query(ContactsContract.Contacts.CONTENT_URI, null ,null , null, null);
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });

    }
    protected void onActivityResult(int reqCode, int resultCode, Intent data){
        super.onActivityResult(reqCode, resultCode, data);
        if(resultCode != RESULT_CANCELED && data != null) {
            if (reqCode == PICK_CONTACT) {
                if (resultCode == RESULT_OK) {
                        Uri contactData = data.getData();
                        Cursor contacts = getContentResolver().query(contactData, null, null, null, null);

                    if (contacts.moveToFirst()) {
                        String contactId = contacts.getString(contacts.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);

                        if (phones == null){
                            showErrorMessage(ErrorMessages.noPhoneNumber);
                        }
                        while (phones.moveToNext()) {
                            int type = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                            if (type == ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE) {
                            }
                        }
                        phones.close();

                        if (!checkIfNewContactId(contactId)) {
                            showErrorMessage(ErrorMessages.contactAlreadyExists);
                            return;
                        } else {
                            contactIdList.add(contactId);
                            saveArrayList(contactIdList, "contactIdList");
                        }
                    }
                    contacts.close();
                }

            }
        }
    }

    protected boolean checkIfNewContactId(String id){

        ListView emergencyContactsListView = (ListView) findViewById(R.id.emergencyContactsList);
        if(contactIdList.isEmpty()) {
            return true;
        } else {
            for(int i = 0; i <= contactIdList.size(); i++){
                if(contactIdList.get(i).equals(id))
                    return false;
            }
        }
        return  true;
    }

    protected void showErrorMessage(CharSequence message){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }


    public void saveArrayList(ArrayList<String> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }

    public ArrayList<String> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void loadContactsListView(){

    }


}
