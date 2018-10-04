package com.example.sam.chat;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import static com.example.sam.chat.R.layout.my_message;

public class ChatAdapter extends BaseAdapter{

    private Context mContext;
    private List<String> message;

    public ChatAdapter(Context context, List<String> name) {

        this.mContext = context;
        this.message = name;
    }


    @Override
    public int getCount() {
        return message.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder = new Holder();

        if(convertView == null){

            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.my_message,parent,false);
            holder.sendText = convertView.findViewById(R.id.message_body);
            holder.receiveText = convertView.findViewById(R.id.message_receive);

            convertView.setTag(holder);

        }else {
            holder = (Holder) convertView.getTag();
        }

        holder.sendText.setText(message.get(position));
        holder.receiveText.setText(message.get(position));

        return convertView;
    }

    public class Holder
    {
        TextView sendText;
        TextView receiveText;
    }
}
