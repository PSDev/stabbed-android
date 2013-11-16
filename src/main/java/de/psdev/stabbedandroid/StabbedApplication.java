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

import android.app.Application;
import dagger.ObjectGraph;

import java.util.List;

public abstract class StabbedApplication extends Application implements Stabbed {

    private ObjectGraph mApplicationGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        // Create the application graph
        mApplicationGraph = ObjectGraph.create(getModules().toArray());
        // Inject ourselves so subclasses will have dependencies fulfilled when this method returns.
        inject(this);
    }

    /**
     * A list of modules to use for the application graph. Subclasses can override this method to
     * provide additional modules provided they call {@code super.getModules()}.
     */
    protected abstract List<Object> getModules();

    @Override
    public void inject(final Object object) {
        mApplicationGraph.inject(object);
    }

    @Override
    public ObjectGraph getObjectGraph() {
        return mApplicationGraph;
    }
}
