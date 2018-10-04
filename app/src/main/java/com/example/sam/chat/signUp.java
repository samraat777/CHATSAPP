package com.example.sam.chat;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
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

    /*private boolean validateName() {
        if (nameStr.toString().length()==0) {
            nameEdit.setError("please enter your name");
            return false;
        } else {
            nameEdit.setError(null);
            return true;
        }
    }

    private boolean validatephoneNumber() {
        if (phoneStr.toString().length()==0) {
            nameEdit.setError("please enter valid number");
            return false;
        } else {
            nameEdit.setError(null);
            return true;
        }
    }


    private boolean validateEmail() {
        if (emailStr.toString().length()==0) {
            emailText.setError("please enter your Email");
            return false;
        } else {
            nameEdit.setError(null);
            return true;
        }
    }*/

    public void submit(View v) {


            nameStr = nameEdit.getEditText().getText().toString().trim();
            phoneStr = phoneEdit.getEditText().getText().toString().trim();
            emailStr = emailText.getEditText().getText().toString().trim();


            //Toast.makeText(this, "info" + nameStr + phoneStr + emailStr, Toast.LENGTH_SHORT).show();
            writeNewUser("1",nameStr,phoneStr,emailStr);

            Intent intent=new Intent(this,first.class);
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
