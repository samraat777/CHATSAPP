package com.example.sam.chat;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class customListview extends ArrayAdapter<String> {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("USER");


    private String[] people;
    private String[] info;
    private Integer[] imgid;
    private Activity context;

    public customListview(Activity context, String[] people,String[] info,Integer[] imgid) {

        super(context, R.layout.listview,people);


        this.context=context;
        this.people=people;
        this.imgid=imgid;
        this.info=info;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View r=convertView;
       ViewHolder viewHolder=null;
       if(r==null)
       {
           LayoutInflater layoutInflater=context.getLayoutInflater();
           r=layoutInflater.inflate(R.layout.listview,null,true);
           viewHolder=new ViewHolder(r);
           r.setTag(viewHolder);
       }
       else{
           viewHolder= (ViewHolder) r.getTag();
       }

       viewHolder.im.setImageResource(imgid[position]);
       viewHolder.tx1.setText(people[position]);
       viewHolder.tx2.setText(info[position]);

        return r;







    }
    class ViewHolder
    {
        TextView tx1;
        TextView tx2;
        ImageView im;
        ViewHolder(View v)
        {
            tx1= v.findViewById(R.id.name);
            tx2=v.findViewById(R.id.info);
            im=v.findViewById(R.id.imageView);

        }
    }
}
