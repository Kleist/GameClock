package dk.kleistsvendsen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.inject.Inject;

import roboguice.activity.RoboActivity;

public class HomeActivity extends RoboActivity {
    private @Inject
    IGameTimer gameTimer;

    TextView timeLeftText;
    TextView timePlayedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        connectButtons_();
    }

    private void connectButtons_() {
        Button startButton = (Button) findViewById(R.id.start_button);
        timeLeftText = (TextView) findViewById(R.id.timeLeftText);
        timePlayedText = (TextView) findViewById(R.id.timePlayedText);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameTimer.startTimer();
                timeLeftText.setText(Long.toString(gameTimer.timePlayed()));
                timePlayedText.setText(Long.toString(gameTimer.tic()));
            }
        });

        Button pauseButton = (Button) findViewById(R.id.pause_button);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameTimer.pauseTimer();
                timeLeftText.setText(Long.toString(gameTimer.timePlayed()));
                timePlayedText.setText(Long.toString(gameTimer.tic()));
            }
        });

        Button resetButton = (Button) findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameTimer.resetTimer();
                timeLeftText.setText(Long.toString(gameTimer.timePlayed()));
                timePlayedText.setText(Long.toString(gameTimer.tic()));
            }
        });
    }
}
