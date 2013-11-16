package de.psdev.stabbedandroid;

import dagger.ObjectGraph;

public interface StabbedContext {
    /**
     * Inject the supplied {@code object} using the contexts graph.
     */
    void inject(Object object);

    ObjectGraph getObjectGraph();
}
