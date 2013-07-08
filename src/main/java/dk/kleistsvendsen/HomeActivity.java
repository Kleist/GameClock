package dk.kleistsvendsen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.inject.Inject;

import roboguice.activity.RoboActivity;

public class HomeActivity extends RoboActivity {
    private @Inject
    IGameTimer gameTimer;

    TextView timeLeftText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        connectButtons_();
    }

    private void connectButtons_() {
        Button startButton = (Button) findViewById(R.id.start_button);
        timeLeftText = (TextView) findViewById(R.id.timeLeftText);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeLeftText.setText("");
                gameTimer.startTimer();
            }
        });

        Button pauseButton = (Button) findViewById(R.id.pause_button);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeLeftText.setText(Integer.toString(gameTimer.timeLeft()));
                gameTimer.pauseTimer();
            }
        });

        Button resetButton = (Button) findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeLeftText.setText("Reset");
                gameTimer.resetTimer();
            }
        });
    }
}
