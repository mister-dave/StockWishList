package layout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class AdapterSlidingTab extends FragmentPagerAdapter {


    public AdapterSlidingTab(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Profile";
            case 1:
                return "Database";
            case 2:
                return "Notifications";
            case 3:
                return "Stock Cards";

        }

        return "Fragment 2";
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentStocks();
            case 1:
                return new FragmentFetchDatabase();
            case 2:
                return new FragmentNotification();
            case 3:
                return new FragmentStockCards();

        }
        return new FragmentStocks();


    }
}