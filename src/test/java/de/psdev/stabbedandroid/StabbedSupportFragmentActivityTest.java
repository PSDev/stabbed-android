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
public class StabbedSupportFragmentActivityTest {
    private ActivityController<TestStabbedSupportFragmentActivity> mActivityController;
    private TestStabbedSupportFragmentActivity mActivity;

    @Before
    public void setUp() throws Exception {
        mActivityController = Robolectric.buildActivity(TestStabbedSupportFragmentActivity.class);
        mActivity = mActivityController.get();
    }

    @Test
    public void testInjectionsShouldBePresentAfterOnCreate() throws Exception {
        assertNull("Context should be null", mActivity.mContext);
        mActivityController.create();
        assertNotNull("Context should be injected after onCreate", mActivity.mContext);
    }

    @Test
    public void testActivityGraphShouldExistAfterOnCreate() throws Exception {
        assertNull("ActivityGraph should not exist", mActivity.getObjectGraph());
        mActivityController.create();
        assertNotNull("ActivityGraph should exist", mActivity.getObjectGraph());
    }

    @Test
    public void testActivityGraphShouldNotExistAfterOnDestroy() throws Exception {
        assertNull("ActivityGraph should not exist", mActivity.getObjectGraph());
        mActivityController.create();
        assertNotNull("ActivityGraph should exist", mActivity.getObjectGraph());
        mActivityController.destroy();
        assertNull("ActivityGraph should not exist anymore", mActivity.getObjectGraph());
    }

    @Test
    public void testInjectShouldWorkAfterOnCreate() throws Exception {
        final InjectTestObject injectTestObject = new InjectTestObject();
        assertNull("mContext in injectTestObject should be null", injectTestObject.mContext);
        mActivity.inject(injectTestObject);
        assertNull("mContext in injectTestObject should still be null", injectTestObject.mContext);
        mActivityController.create();
        mActivity.inject(injectTestObject);
        assertNotNull("mContext in injectTestObject should not be null anymore", injectTestObject.mContext);
        assertSame("mContext should be the application", Robolectric.application, injectTestObject.mContext);
    }

    @After
    public void tearDown() throws Exception {
        mActivityController = null;
        mActivity = null;
    }
}
