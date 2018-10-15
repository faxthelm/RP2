package com.rp.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.rp.myapplication.R;
import com.rp.myapplication.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class AdapterContact extends ArrayAdapter<Contact> {

    private Context mContext;
    private List<Contact> contactList = new ArrayList<>();

    public AdapterContact(@NonNull Context context, ArrayList<Contact> list) {
        super(context, 0 , list);
        mContext = context;
        contactList = list;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent,false);

        Contact currentContact = contactList.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.display_name);
        name.setText(currentContact.getName());

        TextView release = (TextView) listItem.findViewById(R.id.display_number);
        release.setText(currentContact.getPhoneNumber());

        ImageButton remove = (ImageButton) listItem.findViewById(R.id.remove_contact);
        final View finalListItem = listItem;
        remove.setOnClickListener(new ImageButton.OnClickListener(){

            @Override
            public void onClick(View v) {
                contactList.remove(position);
                notifyDataSetChanged();
            }
        });

        return listItem;
    }
}
