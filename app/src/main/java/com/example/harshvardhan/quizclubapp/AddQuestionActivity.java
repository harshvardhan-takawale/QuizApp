package com.example.harshvardhan.quizclubapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by lenovo on 7/12/2017.
 */

public class AddQuestionActivity extends AppCompatActivity {


    public static final int DEFAULT_MSG_LENGTH_LIMIT = 10;
    private Button AddButton;
    private EditText adder_question;
    private EditText adder_optA;
    private EditText adder_optB;
    private EditText adder_optC;
    private EditText adder_optD;
    private EditText adder_ans;
    private FirebaseDatabase mFireBaseDatabase;
    private DatabaseReference mQuestionCardReference;






    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_adder);


        adder_question = (EditText) findViewById(R.id.question_add);
        adder_optA = (EditText) findViewById(R.id.edit_a);
        adder_optB = (EditText) findViewById(R.id.edit_b);
        adder_optC = (EditText) findViewById(R.id.edit_c);
        adder_optD = (EditText) findViewById(R.id.edit_d);
        adder_ans = (EditText) findViewById(R.id.correct_ans);
        AddButton = (Button) findViewById(R.id.addbutton);


        mFireBaseDatabase = FirebaseDatabase.getInstance();
        mQuestionCardReference = mFireBaseDatabase.getReference().child("questions");

        AddButton.setEnabled(false);

        adder_ans.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    AddButton.setEnabled(true);
                } else {
                    AddButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        adder_ans.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

        // Send button sends a message and clears the EditText
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                QuestionForm quest = new QuestionForm(adder_question.getText().toString(),
                        adder_optA.getText().toString(),
                        adder_optB.getText().toString(),
                        adder_optC.getText().toString(),
                        adder_optD.getText().toString(),
                        adder_ans.getText().toString());

                mQuestionCardReference.push().setValue(quest);

                Toast.makeText(getApplicationContext(),"Question Added",Toast.LENGTH_SHORT).show();


                // Clear input box
                adder_question.setText("");
                adder_optA.setText("");
                adder_optB.setText("");
                adder_optC.setText("");
                adder_optD.setText("");
                adder_ans.setText("");

            }
        });





    }
}
