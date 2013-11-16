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
import android.content.IntentFilter;
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
public class StabbedBroadcastReceiverTest {
    private ActivityController<TestStabbedActivity> mActivityController;
    private TestStabbedActivity mActivity;
    private TestStabbedBroadcastReceiver mTestStabbedBroadcastReceiver;
    private Runnable mRunInHandleReceive;

    @Before
    public void setUp() throws Exception {
        mActivityController = Robolectric.buildActivity(TestStabbedActivity.class);
        mActivity = mActivityController.get();
        mRunInHandleReceive = new Runnable() {
            @Override
            public void run() {
                final Context context = mTestStabbedBroadcastReceiver.mContext;
                assertNotNull("mContext should be available", context);
                assertSame("mContext should be application", Robolectric.application, context);
            }
        };
        mTestStabbedBroadcastReceiver = new TestStabbedBroadcastReceiver(mRunInHandleReceive);
    }

    @Test
    public void testDependenciesShouldBeInjectedInHandleReceive() throws Exception {
        mActivityController.create().start().resume().visible();
        assertNull("Context should not be injected", mTestStabbedBroadcastReceiver.mContext);
        mActivity.registerReceiver(mTestStabbedBroadcastReceiver, new IntentFilter("TEST_BROADCAST"));
        mActivity.sendBroadcast(new Intent("TEST_BROADCAST"));
    }

    @After
    public void tearDown() throws Exception {
        mActivityController = null;
        mActivity = null;
        mRunInHandleReceive = null;
        mTestStabbedBroadcastReceiver = null;
    }
}
