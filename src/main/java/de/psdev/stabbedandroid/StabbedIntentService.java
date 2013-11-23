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

import android.app.IntentService;
import dagger.ObjectGraph;

import java.util.List;

public abstract class StabbedIntentService extends IntentService implements StabbedContext {

    private final ExtendedGraphHelper mExtendedGraphHelper = new ExtendedGraphHelper();

    protected StabbedIntentService(final String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mExtendedGraphHelper.onCreate(this, getModules(), this);
    }

    @Override
    public void onDestroy() {
        mExtendedGraphHelper.onDestroy();
        super.onDestroy();
    }

    @Override
    public ObjectGraph getObjectGraph() {
        return mExtendedGraphHelper.getExtendedGraph();
    }

    @Override
    public void inject(final Object object) {
        mExtendedGraphHelper.inject(object);
    }

    /**
     * A list of modules to use for the individual service graph. Subclasses can override this
     * method to provide additional modules provided they call and include the modules returned by
     * calling {@code super.getModules()}.
     */
    protected abstract List<Object> getModules();
}
