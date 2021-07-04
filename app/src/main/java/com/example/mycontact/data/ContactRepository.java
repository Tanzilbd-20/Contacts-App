package com.example.mycontact.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mycontact.model.Contact;
import com.example.mycontact.util.ContactRoomDatabase;

import java.util.List;

public class ContactRepository {

    private ContactDao contactDao;
    private LiveData<List<Contact>> allContacts;

    public ContactRepository(Application application){
        ContactRoomDatabase db = ContactRoomDatabase.getAllContacts(application);
        contactDao = db.contactDao();
        allContacts = contactDao.getAllContacts();
    }
    public void insertContact(Contact contact){
        ContactRoomDatabase.databaseWriteExecutor.execute(() -> {
            contactDao.insertContact(contact);
        });
    }
    public void updateContact(Contact contact){
        ContactRoomDatabase.databaseWriteExecutor.execute(() -> {
            contactDao.updateContact(contact);
        });
    }
    public void deleteSingleContact(Contact contact){
        ContactRoomDatabase.databaseWriteExecutor.execute(() -> {
            contactDao.deleteSingleContact(contact);
        });
    }
    public void deleteAllContacts(){
        contactDao.deleteAllContact();
    }
    public LiveData<Contact> getSingleContact(int id){
        return contactDao.getSingleContact(id);
    }
    public LiveData<List<Contact>> getAllContacts(){
        return contactDao.getAllContacts();
    }


}
