package de.psdev.stabbedandroid;

import android.app.Activity;
import dagger.ObjectGraph;

import java.util.List;

public final class StabbedActivityHelper {

    private ObjectGraph mActivityGraph;

    void onCreate(final Activity activity, final List<Object> modules) {
        // Create the activity graph by .plus-ing our modules onto the application graph.
        final StabbedApplication application = (StabbedApplication) activity.getApplication();
        mActivityGraph = application.getApplicationGraph().plus(modules.toArray());

        // Inject activity so subclasses will have dependencies fulfilled when this method returns.
        mActivityGraph.inject(activity);
    }

    void onDestroy() {
        // Eagerly clear the reference to the activity graph to allow it to be garbage collected as
        // soon as possible.
        mActivityGraph = null;
    }

    /**
     * Inject the supplied {@code object} using the activity-specific graph.
     */
    void inject(final Object object) {
        mActivityGraph.inject(object);
    }

    public ObjectGraph getActivityGraph() {
        return mActivityGraph;
    }
}
