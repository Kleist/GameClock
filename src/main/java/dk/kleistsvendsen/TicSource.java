package dk.kleistsvendsen;

import android.os.SystemClock;

public class TicSource implements ITicSource {
    public TicSource() {
    }

    @Override
    public long tic() {
        return SystemClock.elapsedRealtime() % 1000;
    }
}