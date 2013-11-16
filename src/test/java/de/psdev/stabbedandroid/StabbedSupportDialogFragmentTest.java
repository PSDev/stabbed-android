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

import android.R;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/test/resources/AndroidManifest.xml")
public class StabbedSupportDialogFragmentTest {
    private ActivityController<TestStabbedSupportFragmentActivity> mActivityController;
    private TestStabbedSupportFragmentActivity mActivity;
    private TestStabbedSupportDialogFragment mTestStabbedFragment;

    @Before
    public void setUp() throws Exception {
        mActivityController = Robolectric.buildActivity(TestStabbedSupportFragmentActivity.class);
        mActivity = mActivityController.get();
        mTestStabbedFragment = new TestStabbedSupportDialogFragment();
    }

    @Test
    public void testFragmentShouldBeInjectedAfterOnActivityCreated() throws Exception {
        assertNull("mContext should be null", mTestStabbedFragment.mContext);
        mActivityController.create().start().resume().visible();
        mActivity.getSupportFragmentManager().beginTransaction().add(R.id.content, mTestStabbedFragment).commit();
        assertNotNull("mContext should still now be injected", mTestStabbedFragment.mContext);
    }

    @After
    public void tearDown() throws Exception {
        mActivityController = null;
        mActivity = null;
        mTestStabbedFragment = null;
    }
}
