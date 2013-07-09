package dk.kleistsvendsen;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.inject.Inject;

import roboguice.activity.RoboActivity;

public class HomeActivity extends RoboActivity {
    @Inject
    private IGameTimer gameTimer_;

    private TextView timeLeftText_;
    private TextView timePlayedText_;

    private final Handler updateHandler_ = new Handler();
    private final Runnable updateViewRunner_ = new Runnable() {
        @Override
        public void run() {
            updateView_();
            updateHandler_.postDelayed(this, 50);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        connectButtons_();
        updateHandler_.post(updateViewRunner_);
    }

    private void connectButtons_() {
        Button startButton = (Button) findViewById(R.id.start_button);
        timeLeftText_ = (TextView) findViewById(R.id.timeLeftText);
        timePlayedText_ = (TextView) findViewById(R.id.timePlayedText);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameTimer_.startTimer();
            }
        });

        Button pauseButton = (Button) findViewById(R.id.pause_button);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameTimer_.pauseTimer();
            }
        });

        Button resetButton = (Button) findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameTimer_.resetTimer();
            }
        });
    }
    private void updateView_() {
        timeLeftText_.setText(Long.toString(gameTimer_.tic()));
        timePlayedText_.setText(Long.toString(gameTimer_.timePlayed()));
    }
}
