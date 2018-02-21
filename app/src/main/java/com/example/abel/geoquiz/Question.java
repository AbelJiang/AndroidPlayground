package com.example.abel.geoquiz;

/**
 * Created by abel on 2/16/18.
 */

public class Question {
    private int mTextResId;
    private  boolean mAnswerTure;

    public Question(int text, boolean answer){
        mTextResId=text;
        mAnswerTure=answer;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTure() {
        return mAnswerTure;
    }

    public void setAnswerTure(boolean answerTure) {
        mAnswerTure = answerTure;
    }
}
