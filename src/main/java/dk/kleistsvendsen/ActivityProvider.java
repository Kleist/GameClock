package dk.kleistsvendsen;

import android.app.Activity;
import android.content.Context;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ActivityProvider implements Provider<Activity> {
    @Inject
    Provider<Context> contextProvider;

    public Activity get() {
        return (Activity) contextProvider.get();
    }

}
