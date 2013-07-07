package dk.kleistsvendsen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import javax.inject.Inject;

import roboguice.activity.RoboActivity;

public class HomeActivity extends RoboActivity {
    private @Inject
    IGameTimer gameTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        Button startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameTimer.startTimer();
            }
        });
    }
}
