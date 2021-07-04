package com.example.mycontact.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mycontact.data.ContactRepository;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    public static ContactRepository repository;
    public final LiveData<List<Contact>> allContacts;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        repository = new ContactRepository(application);
        allContacts = repository.getAllContacts();
    }
    public static void insertContact(Contact contact){
        repository.insertContact(contact);
    }
    public static void updateContact(Contact contact){
        repository.updateContact(contact);
    }
    public static LiveData<Contact> getSingleContact(int id){
        return repository.getSingleContact(id);
    }
    public static LiveData<List<Contact>> getAllContacts(){
        return repository.getAllContacts();
    }
    public static void deleteSingleContact(Contact contact){
        repository.deleteSingleContact(contact);
    }
    public static void deleteAllContacts(){
        repository.deleteAllContacts();
    }
}
