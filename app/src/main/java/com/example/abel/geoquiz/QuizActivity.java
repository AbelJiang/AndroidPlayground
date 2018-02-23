package com.example.abel.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private static final String TAG="QUIZ_ACTIVITY";
    private static final String KEY_INDEX="index";
    private static final String CHEATER_STATUS="isCheater";
    private static final int REQUEST_CODE_CHEAT=0;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private int mCurrentIndex=0;
    private int mQuestionCount=0;
    private int mScore=0;
    private boolean mIsCheater=false;


    private final Question[] mQuestionBank={
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
        if(savedInstanceState!=null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mIsCheater=savedInstanceState.getBoolean(CHEATER_STATUS,false);
        }
        Log.d(TAG,"onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        mTrueButton=(Button)findViewById(R.id.true_button);
        mFalseButton=(Button)findViewById(R.id.false_button);
        mNextButton=(Button) findViewById(R.id.next_button);
        mCheatButton=(Button)findViewById(R.id.cheat_button);
        mQuestionTextView=(TextView) findViewById(R.id.question_text);
        updateQuestion();
        mNextButton.setOnClickListener(v->{
            mIsCheater=false;
            mTrueButton.setClickable(true);
            mFalseButton.setClickable(true);
            mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
            updateQuestion();
        });
        mTrueButton.setOnClickListener(v->{
            checkAnswer(true);
            mFalseButton.setClickable(false);
            mTrueButton.setClickable(false);
        });

        mFalseButton.setOnClickListener(v->{
            checkAnswer(false);
            mFalseButton.setClickable(false);
            mTrueButton.setClickable(false);
        });
        mCheatButton.setOnClickListener(v->{
            Intent intent=CheatActivity.newIntent(QuizActivity.this, mQuestionBank[mCurrentIndex].isAnswerTure());
            startActivityForResult(intent,REQUEST_CODE_CHEAT);
        });
    }

    private void updateQuestion(){
        int Question=mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(Question);
    }

    private void checkAnswer(boolean answer){
        int result;
        mQuestionCount++;
        if(mIsCheater){
            result=R.string.judgement_toast;
        }else{
            if(answer==mQuestionBank[mCurrentIndex].isAnswerTure()){
                result=R.string.correct_toast;
                mScore++;
            }else{
                result=R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        if(mQuestionCount==mQuestionBank.length){
            Toast.makeText(this, getResources().getString(R.string.score)+mScore, Toast.LENGTH_SHORT).show();
            mQuestionCount=0;
            mScore=0;
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG,"onSaveInstanceState() called");
        savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);
        savedInstanceState.putBoolean(CHEATER_STATUS,mIsCheater);
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){

        if(resultCode!=RESULT_OK){
            return;
        }else{
            if(requestCode==REQUEST_CODE_CHEAT){
                if(intent!=null){
                    mIsCheater=CheatActivity.wasAnswerShown(intent);
                }
            }
        }

    }
}
