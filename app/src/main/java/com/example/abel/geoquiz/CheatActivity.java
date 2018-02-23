package com.example.abel.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE="com.example.abel.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN="com.example.abel.geoquiz.answer_shown";
    private static final String ANSWER_SHOWN="isAnswerShown";
    private static boolean isAnswerShown=false;
    private static boolean mAnswerIsTrue;
    private Button mShowAnswer;
    private TextView mAnswer;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue){
        Intent intent=new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN,false);
    }

    private void setAnswerShownResult(boolean isAnswerShown){
        Intent intent=new Intent(CheatActivity.this, QuizActivity.class);
        intent.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            isAnswerShown=savedInstanceState.getBoolean(ANSWER_SHOWN,false);
            setAnswerShownResult(isAnswerShown);
        }
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        mShowAnswer=(Button)findViewById(R.id.show_answer_button);
        mAnswer=(TextView)findViewById(R.id.answer_text_view);

        mShowAnswer.setOnClickListener(v->{
            if(mAnswerIsTrue){
                mAnswer.setText(R.string.true_button);
            }else{
                mAnswer.setText(R.string.false_button);
            }
            isAnswerShown=true;
            setAnswerShownResult(isAnswerShown);
        });
    }

    @Override
    public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(ANSWER_SHOWN,isAnswerShown);
    }
}
