package de.psdev.stabbedandroid;

import android.content.Context;
import dagger.ObjectGraph;

import javax.annotation.Nullable;
import java.util.List;

public final class ExtendedGraphHelper {

    @Nullable
    private ObjectGraph mExtendedGraph;

    void onCreate(final Context context, final List<Object> modules, final Object target) {
        // Create the activity graph by .plus-ing our modules onto the application graph.
        final StabbedApplication application = (StabbedApplication) context.getApplicationContext();
        mExtendedGraph = application.getApplicationGraph().plus(modules.toArray());

        // Inject activity so subclasses will have dependencies fulfilled when this method returns.
        mExtendedGraph.inject(target);
    }

    void onDestroy() {
        // Eagerly clear the reference to the activity graph to allow it to be garbage collected as
        // soon as possible.
        mExtendedGraph = null;
    }

    /**
     * Inject the supplied {@code object} using the activity-specific graph.
     */
    void inject(final Object object) {
        if (mExtendedGraph == null) {
            throw new IllegalStateException("Used inject outside of activity lifecycle, or call to onCreate missing.");
        }
        mExtendedGraph.inject(object);
    }

    @Nullable
    public ObjectGraph getExtendedGraph() {
        return mExtendedGraph;
    }
}
