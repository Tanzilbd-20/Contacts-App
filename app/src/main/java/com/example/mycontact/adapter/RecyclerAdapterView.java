package com.example.mycontact.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycontact.R;
import com.example.mycontact.model.Contact;

import java.util.List;

public class RecyclerAdapterView extends RecyclerView.Adapter<RecyclerAdapterView.ViewHolder> {
    private List<Contact> contactList;
    private Context context;
    private OnContactClickListener onContactClickListener;

    public RecyclerAdapterView(List<Contact> contactList, Context context,OnContactClickListener onContactClickListener) {
        this.contactList = contactList;
        this.context = context;
        this.onContactClickListener = onContactClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.recycler_list_view,parent,false);
        return new ViewHolder(view,onContactClickListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterView.ViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.name_text.setText(contact.getName());
        holder.phone_text.setText("Phone : "+contact.getPhone());
        holder.relation_text.setText("Relation : "+contact.getRelation());



    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public OnContactClickListener onContactClickListener;
        public TextView name_text,phone_text,relation_text;
        public ViewHolder(@NonNull View itemView, OnContactClickListener onContactClickListener) {
            super(itemView);
            name_text = itemView.findViewById(R.id.name_row_text_view);
            phone_text = itemView.findViewById(R.id.phone_row_text_view);
            relation_text = itemView.findViewById(R.id.relation_row_text_view);
            this.onContactClickListener = onContactClickListener;

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            onContactClickListener.onClickListener(getAdapterPosition());
        }
    }
    public interface OnContactClickListener{
        void onClickListener(int position);
    }
}
