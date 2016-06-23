package com.ryanzhou.company.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.checkBox1) CheckBox checkBoxQuestion1Answer1;
    @BindView(R.id.checkBox2) CheckBox checkBoxQuestion1Answer2;
    @BindView(R.id.checkBox3) CheckBox checkBoxQuestion1Answer3;
    @BindView(R.id.editTextAnswer3) EditText editTextAnswer3;
    @BindView(R.id.editTextAnswer4) EditText editTextAnswer4;
    @BindView(R.id.editTextAnswer5) EditText editTextAnswer5;

    private int question1CheckBoxNumCorrect = 0;
    private Boolean question2RadioCorrect = false;
    public final String LOG_TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        resignNumberKeyboard();
    }

    private void resignNumberKeyboard(){
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @OnClick({R.id.radioButtonTrue, R.id.radioButtonFalse})
    public void radioAnswer2Check(View view) {
        switch (view.getId()) {
            case R.id.radioButtonTrue:
                question2RadioCorrect = true;
                break;
            case R.id.radioButtonFalse:
                question2RadioCorrect = false;
                break;
        }
    }

    @OnClick({R.id.checkBox1, R.id.checkBox2, R.id.checkBox3})
    public void evaluateCheckBoxes(View view) {
        question1CheckBoxNumCorrect = 0;
        if (checkBoxQuestion1Answer1.isChecked())
            question1CheckBoxNumCorrect++;
        if (checkBoxQuestion1Answer2.isChecked())
            question1CheckBoxNumCorrect++;
        if (checkBoxQuestion1Answer3.isChecked()){
            question1CheckBoxNumCorrect++;
        }

    }

    @OnClick(R.id.buttonSubmit)
    public void submitAnswers(View view) {
        displayScore(calculatedEarnedPoints());
    }

    private void displayScore(int pointsEarned) {
        StringBuilder stringBuilder = new StringBuilder("Earned ");
        stringBuilder.append(String.valueOf( pointsEarned ));
        stringBuilder.append( " out of 7");
        Toast.makeText(getApplicationContext(), stringBuilder.toString(), Toast.LENGTH_SHORT).show();
    }

    private int calculatedEarnedPoints() {
        int totalPoints = 0;
        totalPoints += question1CheckBoxNumCorrect;
        if( question2RadioCorrect )
            totalPoints++;
        if( editTextAnswer3.getText().toString().equals(
                getResources().getString(R.string.question3_answer)) )
            totalPoints++;
        if( editTextAnswer4.getText().toString().equalsIgnoreCase(
                getResources().getString(R.string.question4_answer)) )
            totalPoints++;
        if( editTextAnswer5.getText().toString().equalsIgnoreCase(
                getResources().getString(R.string.question5_answer)) )
            totalPoints++;
        return totalPoints;
    }

}

