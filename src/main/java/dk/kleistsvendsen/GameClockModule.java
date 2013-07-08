package dk.kleistsvendsen;

import com.google.inject.AbstractModule;

public class GameClockModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IGameTimer.class).to(GameTimer.class);
    }
}
