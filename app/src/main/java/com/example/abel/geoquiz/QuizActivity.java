package com.example.abel.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueButton=(Button)findViewById(R.id.true_button);
        mFalseButton=(Button)findViewById(R.id.false_button);
        mTrueButton.setOnClickListener(v-> Toast.makeText(this, R.string.correct_toast, 5).show());
        mFalseButton.setOnClickListener(v-> Toast.makeText(this, R.string.incorrect_toast, 5).show());
    }
}
