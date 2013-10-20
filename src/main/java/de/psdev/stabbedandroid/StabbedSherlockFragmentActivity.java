package de.psdev.stabbedandroid;

import android.os.Bundle;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import dagger.ObjectGraph;

import java.util.List;

public abstract class StabbedSherlockFragmentActivity extends SherlockFragmentActivity {

    private final StabbedActivityHelper mActivityHelper = new StabbedActivityHelper();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityHelper.onCreate(this);
    }

    @Override
    protected void onDestroy() {
        mActivityHelper.onDestroy();
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
        mActivityHelper.inject(object);
    }

    ObjectGraph getActivityGraph() {
        return mActivityHelper.getActivityGraph();
    }
}
