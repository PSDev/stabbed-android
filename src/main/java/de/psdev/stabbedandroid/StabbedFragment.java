package de.psdev.stabbedandroid;

import android.app.Fragment;
import android.os.Bundle;

public abstract class StabbedFragment extends Fragment {

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((StabbedActivity) getActivity()).inject(this);
    }

}
