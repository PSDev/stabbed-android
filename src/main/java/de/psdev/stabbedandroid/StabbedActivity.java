package de.psdev.stabbedandroid;

import android.app.Activity;
import android.os.Bundle;
import dagger.ObjectGraph;

import java.util.List;

public abstract class StabbedActivity extends Activity {

    private final ExtendedGraphHelper mExtendedGraphHelper = new ExtendedGraphHelper();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mExtendedGraphHelper.onCreate(this, getModules(), this);
    }

    @Override
    protected void onDestroy() {
        mExtendedGraphHelper.onDestroy();
        super.onDestroy();
    }

    /**
     * A list of modules to use for the individual activity graph. Subclasses can override this
     * method to provide additional modules provided they call and include the modules returned by
     * calling {@code super.getModules()}.
     */
    protected abstract List<Object> getModules();

    /**
     * Inject the supplied {@code object} using the activity-specific graph.
     */
    void inject(final Object object) {
        mExtendedGraphHelper.inject(object);
    }

    ObjectGraph getActivityGraph() {
        return mExtendedGraphHelper.getExtendedGraph();
    }
}
