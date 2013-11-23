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
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class TestStabbedIntentService extends StabbedIntentService {

    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();

    @Inject
    Context mContext;

    protected TestStabbedIntentService() {
        super(TestStabbedIntentService.class.getName());
    }

    @Override
    protected List<Object> getModules() {
        return Collections.emptyList();
    }

    @Override
    public IBinder onBind(final Intent intent) {
        return mBinder;
    }

    @Override
    protected void onHandleIntent(final Intent intent) {

    }

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        TestStabbedIntentService getService() {
            // Return this instance of LocalService so clients can call public methods
            return TestStabbedIntentService.this;
        }
    }

}
