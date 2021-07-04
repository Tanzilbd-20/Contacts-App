package com.example.mycontact;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mycontact.adapter.RecyclerAdapterView;
import com.example.mycontact.model.Contact;
import com.example.mycontact.model.ContactViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapterView.OnContactClickListener {

    private static final int REQUEST_CODE = 1;
    public static final String CONTACT_ID = "contactId";
    private ContactViewModel model;
    private FloatingActionButton floating_add_button;
    private RecyclerAdapterView recyclerAdapterView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        model = new ViewModelProvider.AndroidViewModelFactory(this.getApplication()).create(ContactViewModel.class);
        floating_add_button = findViewById(R.id.floating_add_button);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        model.allContacts.observe(this, contacts -> {
           recyclerAdapterView = new RecyclerAdapterView(contacts,this,this);

           recyclerView.setAdapter(recyclerAdapterView);

        });

        floating_add_button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,NewContact.class);
            startActivityForResult(intent,REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            assert data != null;
            String name = data.getStringExtra(NewContact.NAME);
            String phone = data.getStringExtra(NewContact.PHONE);
            String relation = data.getStringExtra(NewContact.RELATION);
            Contact contact = new Contact(name,phone,relation);
            ContactViewModel.insertContact(contact);
        }
    }

    @Override
    public void onClickListener(int position) {
        Intent intent = new Intent(MainActivity.this,NewContact.class);
        Contact contact = model.allContacts.getValue().get(position);
        Log.d("MainResult", "onClickListener: "+contact.getName());
        intent.putExtra(CONTACT_ID,contact.getId());
        startActivity(intent);
    }
}