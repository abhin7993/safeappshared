package com.example.hp.safeapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import static com.example.hp.safeapp.R.id.button;

/**
 * Created by AndroidBash on 10/07/16
 */

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "AndroidBash";
    //Add YOUR Firebase Reference URL instead of the following URL
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mRef;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStatetListener;
    private EditText contact1;
    private EditText contact2;
    private EditText contact3;
    private EditText contact4;
    private EditText contact5;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth =FirebaseAuth.getInstance();
        mRef= mFirebaseDatabase.getReference().child("users");
        final Button btn = (Button) findViewById(R.id.btn_signup);


        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                saveNewUser();
                goMainScreen();

            }
        });

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();

        contact1 = (EditText) findViewById(R.id.input_location1);
        contact2 = (EditText) findViewById(R.id.input_location2);
        contact3 = (EditText) findViewById(R.id.input_location3);
        contact4 = (EditText) findViewById(R.id.input_location4);
        contact5 = (EditText) findViewById(R.id.input_location5);


    }








    private void saveNewUser() {

        String userId=mAuth.getCurrentUser().getUid();

        mRef.child(userId).child("contact1").setValue(contact1.getText().toString());
        mRef.child(userId).child("contact2").setValue(contact2.getText().toString());
        mRef.child(userId).child("contact3").setValue(contact3.getText().toString());
        mRef.child(userId).child("contact4").setValue(contact4.getText().toString());
        mRef.child(userId).child("contact5").setValue(contact5.getText().toString());
    }

    public void goMainScreen(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }




    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

}
