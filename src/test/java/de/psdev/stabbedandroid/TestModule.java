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
import android.content.Context;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(injects = {
        // Application
        TestStabbedApplication.class,
        // Activities
        TestStabbedActivity.class,
        TestStabbedSupportFragmentActivity.class,
        TestStabbedSherlockFragmentActivity.class,
        TestStabbedSherlockPreferenceActivity.class,
        // Fragments
        TestStabbedFragment.class,
        TestStabbedListFragment.class,
        TestStabbedSupportFragment.class,
        TestStabbedSupportListFragment.class,
        TestStabbedSherlockFragment.class,
        TestStabbedSherlockListFragment.class,
        TestStabbedDialogFragment.class,
        TestStabbedSupportDialogFragment.class,
        TestStabbedSherlockDialogFragment.class,
        // Receiver
        TestStabbedBroadcastReceiver.class,
        // Service
        TestStabbedService.class,
        // Tests
        ExtendedGraphHelperTest.class,
        InjectTestObject.class
})
public class TestModule {
    private final Application mApplication;

    public TestModule(final Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return mApplication;
    }
}
