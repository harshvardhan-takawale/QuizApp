package com.example.harshvardhan.quizclubapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by lenovo on 7/13/2017.
 */

public class QuestionFormAdapter extends ArrayAdapter<QuestionForm> {

    public QuestionFormAdapter(Context context, int resource, List<QuestionForm> objects) {
        super(context, resource, objects);
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
            if (convertView == null){
                convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.card_view,parent, false);
            }

        TextView question = (TextView) convertView.findViewById(R.id.Card_question);
        RadioGroup group = (RadioGroup) convertView.findViewById(R.id.card_radiogroup);
        RadioButton optionA = (RadioButton) convertView.findViewById(R.id.card_opt_a);
        RadioButton optionB = (RadioButton) convertView.findViewById(R.id.card_opt_b);
        RadioButton optionC = (RadioButton) convertView.findViewById(R.id.card_opt_c);
        RadioButton optionD = (RadioButton) convertView.findViewById(R.id.card_opt_d);

        QuestionForm currentQuestion = getItem(position);
        question.setText(currentQuestion.getQuestion());
        optionA.setText(currentQuestion.getOptA());
        optionB.setText(currentQuestion.getOptB());
        optionC.setText(currentQuestion.getOptC());
        optionD.setText(currentQuestion.getOptD());

        final int  answer = Integer.parseInt(currentQuestion.getAnswer());




        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {


                int index = group.indexOfChild(group.findViewById(checkedId));


                if(index == (answer-1)){
                    Toast.makeText(getContext(),"Correct" ,Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(),"Incorrect",Toast.LENGTH_SHORT).show();
                }
            }
        });






        return convertView;
    }
}
