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

import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Application;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.hardware.input.InputManager;
import android.hardware.usb.UsbManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.nfc.NfcManager;
import android.os.PowerManager;
import android.os.Vibrator;
import android.os.storage.StorageManager;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Default application module which provides standard android classes.
 * <br/>
 * Use it like this:
 * <p><blockquote><pre>
 * {@literal @}Module(includes = { DefaultAndroidApplicationModule.class })
 * </pre></blockquote><p>
 */
@Module(library = true)
public class DefaultAndroidApplicationModule {

    private final Application mApplication;

    public DefaultAndroidApplicationModule(final Application application) {
        mApplication = application;
    }

    // ==========================================================================================================================
    // Global Android Resources
    // ==========================================================================================================================

    @Provides
    @Singleton
    public Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    @ForApplication
    public Context provideApplicationContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    @ForApplication
    public Resources provideResources() {
        return mApplication.getResources();
    }

    // ==========================================================================================================================
    // Persistence
    // ==========================================================================================================================

    @Provides
    @Singleton
    @ForApplication
    public SharedPreferences provideSharedPreferences(final Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    public AssetManager provideAssetManager(final Application application) {
        return application.getAssets();
    }

    // ==========================================================================================================================
    // Android Services
    // ==========================================================================================================================

    @Provides
    @Singleton
    public AccountManager provideAccountManager(final Application application) {
        return getSystemService(application, Context.ACCOUNT_SERVICE);
    }

    @Provides
    @Singleton
    public ActivityManager provideActivityManager(final Application application) {
        return getSystemService(application, Context.ACTIVITY_SERVICE);
    }

    @Provides
    @Singleton
    public AlarmManager provideAlarmManager(final Application application) {
        return getSystemService(application, Context.ALARM_SERVICE);
    }

    @Provides
    @Singleton
    public AudioManager provideAudioManager(final Application application) {
        return getSystemService(application, Context.AUDIO_SERVICE);
    }

    @Provides
    @Singleton
    public ClipboardManager provideClipboardManager(final Application application) {
        return getSystemService(application, Context.CLIPBOARD_SERVICE);
    }

    @Provides
    @Singleton
    public ConnectivityManager provideConnectivityManager(final Application application) {
        return getSystemService(application, Context.CONNECTIVITY_SERVICE);
    }

    @Provides
    @Singleton
    public DownloadManager provideDownloadManager(final Application application) {
        return getSystemService(application, Context.DOWNLOAD_SERVICE);
    }

    @Provides
    @Singleton
    public InputManager provideInputManager(final Application application) {
        return getSystemService(application, Context.INPUT_SERVICE);
    }

    @Provides
    @Singleton
    public LocationManager provideLocationManager(final Application application) {
        return getSystemService(application, Context.LOCATION_SERVICE);
    }

    @Provides
    @Singleton
    public NfcManager provideNfcManager(final Application application) {
        return getSystemService(application, Context.NFC_SERVICE);
    }

    @Provides
    @Singleton
    public NotificationManager provideNotificationManager(final Application application) {
        return getSystemService(application, Context.NOTIFICATION_SERVICE);
    }

    @Provides
    @Singleton
    public PowerManager providePowerManager(final Application application) {
        return getSystemService(application, Context.POWER_SERVICE);
    }

    @Provides
    @Singleton
    public SearchManager provideSearchManager(final Application application) {
        return getSystemService(application, Context.SEARCH_SERVICE);
    }

    @Provides
    @Singleton
    public SensorManager provideSensorManager(final Application application) {
        return getSystemService(application, Context.SENSOR_SERVICE);
    }

    @Provides
    @Singleton
    public StorageManager provideStorageManager(final Application application) {
        return getSystemService(application, Context.STORAGE_SERVICE);
    }

    @Provides
    @Singleton
    public TelephonyManager provideTelephonyManager(final Application application) {
        return getSystemService(application, Context.TELEPHONY_SERVICE);
    }

    @Provides
    @Singleton
    public UsbManager provideUsbManager(final Application application) {
        return getSystemService(application, Context.USB_SERVICE);
    }

    @Provides
    @Singleton
    public Vibrator provideVibrator(final Application application) {
        return getSystemService(application, Context.VIBRATOR_SERVICE);
    }

    @Provides
    @Singleton
    public WifiManager provideWifiManager(final Application application) {
        return getSystemService(application, Context.WIFI_SERVICE);
    }

    // ==========================================================================================================================
    // Private API
    // ==========================================================================================================================

    private <T> T getSystemService(final Application application, final String service) {
        //noinspection unchecked
        return (T) application.getSystemService(service);
    }
}
