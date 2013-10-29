package de.psdev.stabbedandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.List;

public abstract class StabbedBroadcastReceiver extends BroadcastReceiver {

    ExtendedGraphHelper mExtendedGraphHelper = new ExtendedGraphHelper();

    @Override
    public final void onReceive(final Context context, final Intent intent) {
        mExtendedGraphHelper.onCreate(context, getModules(), this);

        handleReceive(context, intent);

        mExtendedGraphHelper.onDestroy();
    }

    /**
     * A list of modules to use for the individual receiver graph. Subclasses can override this
     * method to provide additional modules provided they call and include the modules returned by
     * calling {@code super.getModules()}.
     */
    protected abstract List<Object> getModules();

    protected abstract void handleReceive(final Context context, final Intent intent);
}
