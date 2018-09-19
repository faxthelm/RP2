package com.rp.myapplication;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class RegisterEmergencyContactsActivity extends AppCompatActivity {

    private final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private static final int PICK_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_emergency_contacts);

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

        if(reqCode == PICK_CONTACT){
            if(resultCode == RESULT_OK){
                Uri contactData = data.getData();
                Cursor contacts = getContentResolver().query(contactData, null, null , null, null);

                if(contacts.moveToFirst()){
                    String name = contacts.getString(contacts.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                    String contactId = contacts.getString(contacts.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                    String number = "";

                    Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                    while (phones.moveToNext()) {
                        int type = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                        if (type == ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE){
                            number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            break;
                        }
                    }
                    phones.close();

                    if(number == "") {
                        // contato sem número
                        return;
                    }
                    else if(!checkIfNewPhone(number))
                        return;
                    else {
                        //adicionar a memoria do app (nome e numero)
                    }
                }
                contacts.close();
            }
        }
    }

    protected boolean checkIfNewPhone(String phone){
        ListView emergencyContactsList = (ListView) findViewById(R.id.emergencyContactsList);
        for (int number = 0; number <= emergencyContactsList.getAdapter().getCount() ; number++){
            if( emergencyContactsList.getItemAtPosition(number) == phone)
                return false;
            else if( emergencyContactsList.getItemAtPosition(number) == new StringBuilder("55" + phone))
                return false;
        }
        return  true;
    }

}
