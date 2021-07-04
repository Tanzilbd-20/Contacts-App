package com.example.mycontact.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mycontact.data.ContactDao;
import com.example.mycontact.model.Contact;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Database(entities = {Contact.class},version = 1,exportSchema = false)
public abstract class ContactRoomDatabase extends RoomDatabase {

    public abstract ContactDao contactDao();
    public static volatile ContactRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREAD = 4;
    public static ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREAD);
    public static ContactRoomDatabase getAllContacts(final Context context){
        if (INSTANCE == null) {
            synchronized (ContactRoomDatabase.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),ContactRoomDatabase.class,"contacts_book")
                            .addCallback(roomDatabaseCallBack)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback roomDatabaseCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                ContactDao contactDao = INSTANCE.contactDao();
                contactDao.deleteAllContact();
                Contact contact = new Contact("Tanzil","+601127273755","Own");
                contactDao.insertContact(contact);
            });
        }
    };
}
