package com.example.mycontact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mycontact.model.Contact;
import com.example.mycontact.model.ContactViewModel;
import com.google.android.material.snackbar.Snackbar;

public class NewContact extends AppCompatActivity {

    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String RELATION = "relation";
    private ContactViewModel viewModel;
    private EditText name_edit_text,phone_edit_text,relation_edit_text;
    private Button save_button,delete_button,update_button;
    private int contact_id = 0;
    private Boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        viewModel = new ViewModelProvider.AndroidViewModelFactory(this.getApplication()).create(ContactViewModel.class);
        name_edit_text = findViewById(R.id.name_edit_text);
        phone_edit_text = findViewById(R.id.phone_edit_text);
        relation_edit_text = findViewById(R.id.relation_edit_text);
        save_button = findViewById(R.id.save_button);
        delete_button = findViewById(R.id.delete_button);
        update_button = findViewById(R.id.update_button);


        Bundle data = getIntent().getExtras();
        if(getIntent().hasExtra(MainActivity.CONTACT_ID)){
            contact_id = data.getInt(MainActivity.CONTACT_ID,0);

            ContactViewModel.getSingleContact(contact_id).observe(this, contact -> {
                if(contact !=null){
                    name_edit_text.setText(contact.getName());
                    phone_edit_text.setText(contact.getPhone());
                    relation_edit_text.setText(contact.getRelation());
                }
            });
            isEdit = true;

        }



        save_button.setOnClickListener(view -> {

            Intent sendInfo = new Intent();
            if(TextUtils.isEmpty(name_edit_text.getText()) || TextUtils.isEmpty(phone_edit_text.getText())){
                Snackbar.make(name_edit_text,"Please Insert Name and Phone",Snackbar.LENGTH_SHORT).show();
            }else {
                String relationNotProvided = "Not Provided";
                String name = name_edit_text.getText().toString();
                String phone = phone_edit_text.getText().toString();
                String relation = relation_edit_text.getText().toString();
                sendInfo.putExtra(NAME,name);
                sendInfo.putExtra(PHONE,phone);
                if(relation.isEmpty()){
                    sendInfo.putExtra(RELATION,relationNotProvided);
                }else {
                    sendInfo.putExtra(RELATION,relation);
                }
                setResult(RESULT_OK,sendInfo);
                finish();
            }

        });
        update_button.setOnClickListener(view -> {
            String name = name_edit_text.getText().toString();
            String phone = phone_edit_text.getText().toString();
            String relation = relation_edit_text.getText().toString();
            if(!TextUtils.isEmpty(name)|| !TextUtils.isEmpty(phone)){
                Contact contact = new Contact();
                contact.setId(contact_id);
                contact.setName(name);
                contact.setPhone(phone);
                if(relation.isEmpty()){
                    contact.setRelation("Not Provided");
                }else {
                    contact.setRelation(relation);
                }
                ContactViewModel.updateContact(contact);
            finish();
            }else {
                Snackbar.make(name_edit_text,"Please Insert Name and Phone",Snackbar.LENGTH_SHORT).show();
            }

        });

        delete_button.setOnClickListener(view -> {
            String name = name_edit_text.getText().toString();
            String phone = phone_edit_text.getText().toString();
            String relation = relation_edit_text.getText().toString();
            if(!TextUtils.isEmpty(name)|| !TextUtils.isEmpty(phone)){
                Contact contact = new Contact();
                contact.setId(contact_id);
                contact.setName(name);
                contact.setPhone(phone);
                if(relation.isEmpty()){
                    contact.setRelation("Not Provided");
                }else {
                    contact.setRelation(relation);
                }
                ContactViewModel.deleteSingleContact(contact);
                finish();
            }else {
                Snackbar.make(name_edit_text,"Please Insert Name and Phone",Snackbar.LENGTH_SHORT).show();
            }
        });

        if(isEdit){
            save_button.setVisibility(View.GONE);
        }else {
            update_button.setVisibility(View.GONE);
            delete_button.setVisibility(View.GONE);
        }

    }
}