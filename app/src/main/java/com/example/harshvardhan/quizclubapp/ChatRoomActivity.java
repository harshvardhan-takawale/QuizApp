package com.example.harshvardhan.quizclubapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 7/12/2017.
 */

public class ChatRoomActivity extends AppCompatActivity {

    public static final String ANONYMOUS = "anonymous";
    private FirebaseDatabase mDataBase;
    private DatabaseReference mMessageReferece;
    private ChildEventListener mMessageListener;
    private FirebaseUser user;
    private ListView listofmessages;
    private EditText message;
    private Button sendbutton;
    private ProgressBar mprogressbar;
    private String mUsername;
    private MessageFormAdapter mAdapter;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messanger);

        mUsername = ANONYMOUS;
        mDataBase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mMessageReferece = mDataBase.getReference().child("messages");
        user = mAuth.getCurrentUser();


        listofmessages = (ListView) findViewById(R.id.messageListView);
        sendbutton = (Button) findViewById(R.id.sendButton);
        message = (EditText) findViewById(R.id.messageEditText);



        List<MessageForm> messageinList = new ArrayList<>();
        mAdapter = new MessageFormAdapter(this,R.layout.item_message,messageinList);
        listofmessages.setAdapter(mAdapter);




        message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    sendbutton.setEnabled(true);
                } else {
                    sendbutton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageForm mess = new MessageForm(message.getText().toString(),user.getDisplayName());
                mMessageReferece.push().setValue(mess);

                message.setText("");

            }
        });

        if(user != null){
            onSignedInInitialize(user.getDisplayName());
        }else {
            onSignedOutCleanup();
        }

    }


    @Override
    protected void onPause() {
        super.onPause();

        mAdapter.clear();
        detachDatabaseReadListener();
    }

    private void onSignedInInitialize(String username) {
        mUsername = username;
        attachDatabaseReadListener();
    }

    private void onSignedOutCleanup() {
        mUsername = ANONYMOUS;
        mAdapter.clear();
        detachDatabaseReadListener();
    }

    private void attachDatabaseReadListener() {
        if (mMessageListener == null) {
            mMessageListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    MessageForm friendlyMessage = dataSnapshot.getValue(MessageForm.class);
                    mAdapter.add(friendlyMessage);
                }

                public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                public void onChildRemoved(DataSnapshot dataSnapshot) {}
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                public void onCancelled(DatabaseError databaseError) {}
            };
            mMessageReferece.addChildEventListener(mMessageListener);
        }
    }

    private void detachDatabaseReadListener() {
        if (mMessageListener != null) {
            mMessageReferece.removeEventListener(mMessageListener);
            mMessageListener = null;
        }
    }


}
