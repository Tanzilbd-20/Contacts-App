package com.example.mycontact.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts_book")
public class Contact {

    @PrimaryKey(autoGenerate = true)
    private int id ;
    @ColumnInfo(name = "Name")
    private String name;
    @ColumnInfo(name = "Phone")
    private String phone;
    @ColumnInfo(name = "Relation")
    private String relation;

    public Contact() {
    }

    public Contact(@NonNull String name,@NonNull String phone,@NonNull String relation) {

        this.name = name;
        this.phone = phone;
        this.relation = relation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
