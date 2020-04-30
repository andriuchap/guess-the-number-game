package edu.ktu.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView turnsText;
    TextView guessRange;
    TextView resultText;
    EditText guessField;
    Button guessBtn;

    int minNumber = 1;
    int maxNumber = 100;
    int randomNumber;

    int maxTurns = 7;
    int currentTurn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turnsText = findViewById(R.id.turns);
        guessRange = findViewById(R.id.range);
        resultText = findViewById(R.id.result);
        guessField = findViewById(R.id.guess_field);
        guessBtn = findViewById(R.id.guess_btn);

        resultText.setVisibility(View.INVISIBLE);

        guessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guessClick(v);
            }
        });

        Random random = new Random();
        randomNumber = random.nextInt() % maxNumber + minNumber;
        guessRange.setText(String.format(getResources().getString(R.string.format_guess_range), minNumber, maxNumber));

        updateTurn();
    }

    protected void updateTurn()
    {
        turnsText.setText(String.format(getResources().getString(R.string.format_turns), maxTurns - currentTurn));
    }

    public void guessClick(View view)
    {
        resultText.setVisibility(View.VISIBLE);
        int number = Integer.parseInt(guessField.getText().toString());
        currentTurn++;
        updateTurn();
        boolean win = false;

        String resultString = "";

        if(number > randomNumber)
        {
            resultString = String.format(getResources().getString(R.string.format_result_high), number);
        }
        else if (number < randomNumber)
        {
            resultString = String.format(getResources().getString(R.string.format_result_low), number);
        }
        else
        {
            win = true;
        }
        resultText.setText(resultString);
        if(win || currentTurn == maxTurns)
        {
            Intent intent = new Intent(this, GameOverActivity.class);
            intent.putExtra("win", win);
            intent.putExtra("number", randomNumber);
            startActivity(intent);
            finish();
        }
    }
}
