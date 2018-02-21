package com.example.abel.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private int mCurrentIndex=0;

    private Question[] mQuestionBank={
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans,true),
            new Question(R.string.question_mideast,false),
            new Question(R.string.question_africa,false),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_asia,true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueButton=(Button)findViewById(R.id.true_button);
        mFalseButton=(Button)findViewById(R.id.false_button);
        mNextButton=(Button) findViewById(R.id.next_button);
        mQuestionTextView=(TextView)findViewById(R.id.question_text);
        updateQuestion();
        mNextButton.setOnClickListener(v->{
            mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
            updateQuestion();
        });
        mTrueButton.setOnClickListener(v->{
            checkAnswer(true);
        });

        mFalseButton.setOnClickListener(v->{
            checkAnswer(false);
        });
    }

    private void updateQuestion(){
        int Question=mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(Question);
    }

    private void checkAnswer(boolean answer){
        int result;
        if(answer==mQuestionBank[mCurrentIndex].isAnswerTure()){
            result=R.string.correct_toast;
        }else{
            result=R.string.incorrect_toast;
        }
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }
}
