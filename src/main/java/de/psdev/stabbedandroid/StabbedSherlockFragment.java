package de.psdev.stabbedandroid;

import android.os.Bundle;
import com.actionbarsherlock.app.SherlockFragment;

public abstract class StabbedSherlockFragment extends SherlockFragment {

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((StabbedSherlockFragmentActivity) getActivity()).inject(this);
    }

}
