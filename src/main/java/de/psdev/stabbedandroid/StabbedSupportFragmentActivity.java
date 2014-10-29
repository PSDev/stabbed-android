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

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import dagger.ObjectGraph;

import java.util.List;

public abstract class StabbedSupportFragmentActivity extends FragmentActivity implements StabbedContext {

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

    @Override
    public void inject(final Object object) {
        mExtendedGraphHelper.inject(object);
    }

    @Override
    public ObjectGraph getObjectGraph() {
        return mExtendedGraphHelper.getExtendedGraph();
    }
}
