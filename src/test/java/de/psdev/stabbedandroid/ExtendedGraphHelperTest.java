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
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/test/resources/AndroidManifest.xml")
public class ExtendedGraphHelperTest {

    private TestStabbedActivity mTestStabbedActivity;
    private ExtendedGraphHelper mExtendedGraphHelper;

    // Injections
    @Inject
    Context mContext;

    @Before
    public void setUp() throws Exception {
        mTestStabbedActivity = Robolectric.buildActivity(TestStabbedActivity.class).create().start().resume().visible().get();
        mExtendedGraphHelper = new ExtendedGraphHelper();
    }

    @Test
    public void testOnCreate() throws Exception {
        assertNull("Context should not yet be injected", mContext);
        mExtendedGraphHelper.onCreate(mTestStabbedActivity, ((TestStabbedApplication) Robolectric.application).getModules(), this);
        assertNotNull("Context should be injected", mContext);
        Assert.assertSame("Injected context should be current application", Robolectric.application, mContext);
    }

    @Test
    public void testOnDestroy() throws Exception {
        mExtendedGraphHelper.onCreate(mTestStabbedActivity, ((TestStabbedApplication) Robolectric.application).getModules(), this);
        assertNotNull("Extended graph should exist", mExtendedGraphHelper.getExtendedGraph());
        mExtendedGraphHelper.onDestroy();
        assertNull("Extended graph should not exist anymore", mExtendedGraphHelper.getExtendedGraph());
    }

    @Test
    public void testInject() throws Exception {
        mExtendedGraphHelper.onCreate(mTestStabbedActivity, ((TestStabbedApplication) Robolectric.application).getModules(), this);
        final InjectTestObject injectTestObject = new InjectTestObject();
        assertNull("Context should not yet be injected", injectTestObject.mContext);
        mExtendedGraphHelper.inject(injectTestObject);
        assertNotNull("Context should be injected", injectTestObject.mContext);
    }

    @Test
    public void testGetExtendedGraph() throws Exception {
        mExtendedGraphHelper.onCreate(mTestStabbedActivity, ((TestStabbedApplication) Robolectric.application).getModules(), this);
        assertNotNull("Extended graph should exist", mExtendedGraphHelper.getExtendedGraph());
    }

    @After
    public void tearDown() throws Exception {
        mTestStabbedActivity = null;
        mExtendedGraphHelper = null;
        mContext = null;
    }
}
