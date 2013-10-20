package de.psdev.stabbedandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public abstract class StabbedSupportFragment extends Fragment {

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((StabbedSupportFragmentActivity) getActivity()).inject(this);
    }

}
