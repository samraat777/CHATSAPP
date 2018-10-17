package com.example.sam.chat;

import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import static android.support.transition.Transition.ArrayListManager.add;

public class First extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ArrayAdapter<String> adapter;;
    customListview custom;
    ContentResolver resolver;
    private static int PICK_CONTACT = 1;
    TextView UserName;
    public static String name;
    TextView phoneNumber;
    View headerView;
    List<User> value;

   // FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("USER");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);




        phoneNumber = findViewById(R.id.phoneNumber);

        value = new ArrayList<User>();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        final ListView lv = findViewById(R.id.userName);
        String[] people = {"Samraat","Sonu","Shubraj","Madhav","Saikat","SGP"};
        String[] info = {"Owner","Friend","Senior","Happy new year","dslr","Teacher"};
        Integer[] imgid = {R.drawable.samraat,R.drawable.sonukr,R.drawable.shubraj,R.drawable.madhav,R.drawable.saikat,R.drawable.sgp};


        custom=new customListview(this,people,info,imgid);
        lv.setAdapter(custom);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(First.this, "Person selected: "+ lv.getItemAtPosition(position), Toast.LENGTH_SHORT).show();

               // myRef.child(lv.getItemAtPosition(position).toString()).child(lv.getItemAtPosition(position).toString()+" to user").push().setValue(chatPage.msg);
               // myRef.child(lv.getItemAtPosition(position).toString()).child("user to "+lv.getItemAtPosition(position).toString()).push().setValue("hii "+lv.getItemAtPosition(position).toString());

                Intent intent=new Intent(First.this,chatPage.class);
                intent.putExtra("PersonClicked",lv.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(First.this);
        headerView = navigationView.getHeaderView(0);
        UserName = headerView.findViewById(R.id.userName);
        phoneNumber = headerView.findViewById(R.id.phoneNumber);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    User user = ds.getValue(User.class);
                    UserName.setText(user.getUsername());
                    Log.i("_name",user.getUsername());
                    name = user.getUsername();
                    phoneNumber.setText(user.phoneNumber);


                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i("TAG", "Failed to read value.", error.toException());
            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.first, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                custom.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_newGroup) {
            Toast.makeText(this, "NEW GROUP", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.nav_contact) {




        } else if (id == R.id.nav_Setting) {
            Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.nav_share) {
            Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.nav_send) {
            Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void nav_header_onclick(View view)
    {
        Intent intent=new Intent(this,UserProfilePage.class);
        startActivity(intent);
    }
    public void fab(View view)
    {
        Intent intent=new Intent(this,UserProfilePage.class);
        startActivity(intent);
    }

}
