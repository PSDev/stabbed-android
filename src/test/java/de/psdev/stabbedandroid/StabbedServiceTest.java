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

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/test/resources/AndroidManifest.xml")
public class StabbedServiceTest {
    private ActivityController<TestStabbedActivity> mActivityController;
    private TestStabbedActivity mActivity;
    private TestStabbedService mTestStabbedService;

    @Before
    public void setUp() throws Exception {
        mActivityController = Robolectric.buildActivity(TestStabbedActivity.class);
        mActivity = mActivityController.get();
        mTestStabbedService = Robolectric.newInstanceOf(TestStabbedService.class);
    }

    @Test
    public void testBoundServiceHasDependencyInjected() throws Exception {
        mActivityController.create().start().resume().visible();
        final Intent serviceIntent = new Intent(Robolectric.application, TestStabbedService.class);
        final String servicePackageName = TestStabbedService.class.getPackage().getName();
        final ComponentName name = new ComponentName(servicePackageName, TestStabbedService.class.getName());
        assertNull("ServiceGraph should not yet exist", mTestStabbedService.getServiceGraph());
        mTestStabbedService.onCreate();
        final IBinder serviceBinder = mTestStabbedService.onBind(serviceIntent);
        Robolectric.getShadowApplication().setComponentNameAndServiceForBindService(name, serviceBinder);
        assertTrue("Service has been bound", mActivity.bindService(serviceIntent, new ServiceConnection() {
            @Override
            public void onServiceConnected(final ComponentName name, final IBinder service) {
                TestStabbedService.LocalBinder binder = (TestStabbedService.LocalBinder) service;
                assertNotNull("ServiceGraph should exist", binder.getService().getServiceGraph());
                assertNotNull("mContext should be injected", binder.getService().mContext);
                assertSame("mContext should be Application", Robolectric.application, binder.getService().mContext);
            }

            @Override
            public void onServiceDisconnected(final ComponentName name) {
            }
        }, Context.BIND_AUTO_CREATE));
        mTestStabbedService.onDestroy();
        assertNull("ServiceGraph should not exist anymore", mTestStabbedService.getServiceGraph());
    }

    @After
    public void tearDown() throws Exception {
        mActivityController = null;
        mActivity = null;
        mTestStabbedService = null;
    }
}
