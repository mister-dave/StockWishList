package layout;

import android.os.Bundle;

import com.example.david.stockwishlist.R;

import roboguice.RoboGuice;

/**
 * Created by David on 12/28/16.
 */

public class FragmentGreenScreen extends BaseFragment{

    @Override
    int getFragmentLayoutResourceId() {
        return R.layout.fragment_green_screen;
    }

    @Override
    void findViews() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.setHasOptionsMenu(true);
        RoboGuice.injectMembers(getActivity().getApplicationContext(), this);
    }
}
