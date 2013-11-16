/*
 * Copyright 2013 Philip Schiffer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.psdev.stabbedandroid;

import android.content.Context;
import dagger.ObjectGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.List;

public final class ExtendedGraphHelper {
    private static final Logger LOG = LoggerFactory.getLogger(ExtendedGraphHelper.class);

    @Nullable
    private ObjectGraph mExtendedGraph;

    void onCreate(final Context context, final List<Object> modules, final Object target) {
        // Create the activity graph by .plus-ing our modules onto the application graph.
        final StabbedContext application = (StabbedContext) context.getApplicationContext();
        mExtendedGraph = application.getObjectGraph().plus(modules.toArray());

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
        if (mExtendedGraph != null) {
            mExtendedGraph.inject(object);
        } else {
            LOG.warn("Used inject outside of activity lifecycle, or call to onCreate missing.");
        }
    }

    @Nullable
    public ObjectGraph getExtendedGraph() {
        return mExtendedGraph;
    }
}
