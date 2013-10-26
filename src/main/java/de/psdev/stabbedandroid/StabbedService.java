package de.psdev.stabbedandroid;

import android.app.Service;

import java.util.List;

public abstract class StabbedService extends Service {

    private final ExtendedGraphHelper mExtendedGraphHelper = new ExtendedGraphHelper();

    @Override
    public void onCreate() {
        super.onCreate();
        mExtendedGraphHelper.onCreate(this, getModules());
    }

    @Override
    public void onDestroy() {
        mExtendedGraphHelper.onDestroy();
        super.onDestroy();
    }

    /**
     * A list of modules to use for the individual service graph. Subclasses can override this
     * method to provide additional modules provided they call and include the modules returned by
     * calling {@code super.getModules()}.
     */
    protected abstract List<Object> getModules();
}
