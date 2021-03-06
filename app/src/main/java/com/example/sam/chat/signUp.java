package com.example.sam.chat;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class signUp extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;

    String nameStr;
    String phoneStr;
    String emailStr;
    private TextInputLayout nameEdit;
    private TextInputLayout phoneEdit;
    private TextInputLayout emailText;
    CircleImageView userImage;
    Uri mImageUri;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("USER");
    //private StorageReference





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameEdit = findViewById(R.id.name);
        nameStr = nameEdit.getEditText().getText().toString().trim();
        phoneEdit = findViewById(R.id.phone);
        phoneStr = phoneEdit.getEditText().getText().toString().trim();
        emailText = findViewById(R.id.email);
        emailStr = emailText.getEditText().getText().toString().trim();
        userImage = findViewById(R.id.userImage);






    }

    public void profilePicLoad(View v) {
        openGallery();
    }

    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Picasso
                    .get()
                    .load(mImageUri)
                    .into(userImage);
        }
    }



    public void submit(View v) {


            nameStr = nameEdit.getEditText().getText().toString().trim();
            phoneStr = phoneEdit.getEditText().getText().toString().trim();
            emailStr = emailText.getEditText().getText().toString().trim();


            //Toast.makeText(this, "info" + nameStr + phoneStr + emailStr, Toast.LENGTH_SHORT).show();
            writeNewUser("1",nameStr,phoneStr,emailStr);

            //Intent intent=new Intent(this,signIn.class);
            Intent intent=new Intent(this,First.class);
            startActivity(intent);









    }

    private void writeNewUser(String userId, String name, String phoneNumber, String email) {
        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        myRef.push().setValue(user);
    }


}
