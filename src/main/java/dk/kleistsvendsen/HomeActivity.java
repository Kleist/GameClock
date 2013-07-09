package dk.kleistsvendsen;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.inject.Inject;

import roboguice.activity.RoboActivity;

public class HomeActivity extends RoboActivity {
    public static final int MilliSecsPerMinute = 60 * 1000;
    public static final int MilliSecsPerHalf = 20 * MilliSecsPerMinute;
    @Inject
    private IGameTimer gameTimer_;

    private TextView timeLeftText_;
    private TextView timePlayedText_;

    private final Handler updateHandler_ = new Handler();
    private final Runnable updateViewRunner_ = new Runnable() {
        @Override
        public void run() {
            updateView();
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

    public void updateView() {
        Long played = gameTimer_.timePlayed();
        assert(played<= MilliSecsPerHalf);
        Long left = MilliSecsPerHalf - played;
        timeLeftText_.setText(formatTime_(played));
        timePlayedText_.setText(formatTime_(left));
    }

    private String formatTime_(Long left) {
        int minutes = (int)(left%MilliSecsPerMinute);
        double seconds = (left-(MilliSecsPerMinute*minutes))/1000.0;
        return String.format("%d:%.2f", minutes, seconds);
    }
}
