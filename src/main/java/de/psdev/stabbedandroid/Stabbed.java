package de.psdev.stabbedandroid;

import dagger.ObjectGraph;

public interface Stabbed {
    /**
     * Inject the supplied {@code object} using the contexts graph.
     */
    void inject(Object object);

    ObjectGraph getObjectGraph();
}
