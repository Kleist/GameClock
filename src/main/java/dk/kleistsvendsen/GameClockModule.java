package dk.kleistsvendsen;

import com.google.inject.AbstractModule;

/**
 * Created by andreas on 07/07/13.
 */
public class GameClockModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IGameTimer.class).to(GameTimer.class);
    }
}
