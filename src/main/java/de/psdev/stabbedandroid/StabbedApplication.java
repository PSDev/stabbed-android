package de.psdev.stabbedandroid;

import android.app.Application;
import dagger.ObjectGraph;

import java.util.List;

public abstract class StabbedApplication extends Application {

    private ObjectGraph mApplicationGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        // Create the application graph
        mApplicationGraph = ObjectGraph.create(getModules().toArray());
        // Inject ourselves so subclasses will have dependencies fulfilled when this method returns.
        mApplicationGraph.inject(this);
    }

    /**
     * A list of modules to use for the application graph. Subclasses can override this method to
     * provide additional modules provided they call {@code super.getModules()}.
     */
    protected abstract List<Object> getModules();

    ObjectGraph getApplicationGraph() {
        return mApplicationGraph;
    }
}
