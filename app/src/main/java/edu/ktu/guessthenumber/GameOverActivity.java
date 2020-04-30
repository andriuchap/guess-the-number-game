package edu.ktu.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        boolean win = extras.getBoolean("win");
        int number = extras.getInt("number");

        TextView gameResultText = findViewById(R.id.game_result);
        TextView numberText = findViewById(R.id.number);

        if(win)
        {
            gameResultText.setText(getResources().getString(R.string.win_msg));
            gameResultText.setTextColor(getResources().getColor(R.color.win));
        }
        else
        {
            gameResultText.setText(getResources().getString(R.string.lose_msg));
            gameResultText.setTextColor(getResources().getColor(R.color.lose));
        }

        numberText.setText(String.format(getResources().getString(R.string.number), number));
    }
}
