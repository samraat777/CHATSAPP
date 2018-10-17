package com.example.sam.chat;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ChatAdapter extends BaseAdapter {

    private Context mContext;
    private List<RetrieveMessage> message;
    RetrieveMessage newmessage;
    private List<RetrieveMessage> rMessage;


    public ChatAdapter(Context context, List<RetrieveMessage> msg, List<RetrieveMessage> rMessage) {

        this.mContext = context;
        this.message = msg;
        this.rMessage = rMessage;
    }


    @Override
    public int getCount() {


        int max = message.size();
        if (max < rMessage.size()) {
            max = rMessage.size();
        }
        return max;
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

        if (convertView == null) {

            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.my_message, parent, false);
            holder.sendText = convertView.findViewById(R.id.message_body);
            holder.receiveText = convertView.findViewById(R.id.message_receive);

            convertView.setTag(holder);

        } else {
            holder = (Holder) convertView.getTag();
        }

        //holder.sendText.setText(message.get(position));
        if (message.size() !=0) {

            newmessage = message.get(position);

            //Log.i("_msg", newmessage.getMessage());

            holder.sendText.setText(newmessage.getMessage());
        }
        if (rMessage.size() != 0) {
        RetrieveMessage rtr = rMessage.get(position);

            holder.receiveText.setText(rtr.getMessage());
        }

        return convertView;
    }

    public class Holder {
        TextView sendText;
        TextView receiveText;
    }
}
