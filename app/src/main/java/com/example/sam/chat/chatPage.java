package com.example.sam.chat;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class chatPage extends AppCompatActivity {

    ListView listView;
    EditText chat_text;
    ImageView send;
    ChatAdapter adapter;
    List<String> messages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);

        listView = findViewById(R.id.messages_view);
        chat_text = findViewById(R.id.messageEditText);
        send = findViewById(R.id.sendImage);
        // listView.setAdapter(adapter);
        messages = new ArrayList<>();

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


    }

    public void send(View view) {

        String msg = chat_text.getText().toString().trim();
        messages.add(msg);
        adapter = new ChatAdapter(chatPage.this, messages);
        listView.setAdapter(adapter);
        chat_text.setText("");
        scrollMyListViewToBottom();

    }

    private void scrollMyListViewToBottom() {
        listView.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                listView.setSelection(adapter.getCount() - 1);
            }
        });
    }
}