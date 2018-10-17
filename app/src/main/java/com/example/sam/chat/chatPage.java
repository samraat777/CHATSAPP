package com.example.sam.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class chatPage extends AppCompatActivity {

    ListView listView;
    EditText chat_text;
    ImageView send;
    ChatAdapter adapter;
    List<RetrieveMessage> messages;
    List<RetrieveMessage> rMessages;
    public static String msg;
    String personClicked;
    RetrieveMessage retrieve;


    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);
        personClicked = getIntent().getStringExtra("PersonClicked");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Chats").child(First.name + "_" + personClicked);
        mReference = FirebaseDatabase.getInstance().getReference("Chats")
                .child(personClicked + "_" + First.name);

        listView = findViewById(R.id.messages_view);
        chat_text = findViewById(R.id.messageEditText);
        send = findViewById(R.id.sendImage);
        // listView.setAdapter(adapter);
        messages = new ArrayList<>();
        rMessages = new ArrayList<>();




        chat_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                send.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (chat_text.getText().toString().matches("")) {
                    return;
                } else if (chat_text.getText().toString().matches(" +")) {
                    return;
                } else {
                    send.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {
                // send.setVisibility(View.VISIBLE);
            }
        });


        recieveMessage();

    }

    private void recieveMessage() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                messages.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    RetrieveMessage retriveM = ds.getValue(RetrieveMessage.class);
                    messages.add(retriveM);
                    //  Log.i("_msg", messages.get(0).getMessage());

                }
                //  Log.i("_msg",messages.get(0).getMessage());
                adapter = new ChatAdapter(chatPage.this, messages,rMessages);
                //Log.i("_msg",messages.get(1).toString());
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rMessages.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren() )
                {
                    RetrieveMessage retrieveMessage = ds.getValue(RetrieveMessage.class);
                    rMessages.add(retrieveMessage);

                }
                adapter = new ChatAdapter(chatPage.this,messages,rMessages);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void send(View view) {

        msg = chat_text.getText().toString().trim();

        //adapter = new ChatAdapter(chatPage.this, messages);
        //listView.setAdapter(adapter);
        chat_text.setText("");
        scrollMyListViewToBottom();
        Log.i("_name", First.name);

        retrieve = new RetrieveMessage();
        retrieve.setMessage(chatPage.msg);
        myRef.push().setValue(retrieve);
        recieveMessage();


    }


    private void scrollMyListViewToBottom() {
        listView.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                //  listView.setSelection(adapter.getCount() - 1);
            }
        });
    }
}
