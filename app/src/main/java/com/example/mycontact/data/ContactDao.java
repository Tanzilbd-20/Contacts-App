package com.example.mycontact.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mycontact.model.Contact;

import java.util.List;

@Dao
public interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertContact(Contact contact);
    @Update
    void updateContact(Contact contact);
    @Query("SELECT * FROM contacts_book WHERE contacts_book.id == :id")
    LiveData<Contact> getSingleContact(int id);
    @Query("SELECT * FROM contacts_book ORDER BY Name ASC")
    LiveData<List<Contact>> getAllContacts();
    @Delete
    void deleteSingleContact(Contact contact);
    @Query("DELETE FROM contacts_book")
    void deleteAllContact();
}
