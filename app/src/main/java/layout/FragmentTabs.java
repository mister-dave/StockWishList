package layout;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.astuetz.PagerSlidingTabStrip;
import com.example.david.stockwishlist.R;


public class FragmentTabs extends BaseFragment implements OnPageChangeListener {


    public static BaseFragment newInstance() {
        Bundle bundle = new Bundle();
        BaseFragment fragment = new FragmentTabs();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    int getFragmentLayoutResourceId() {
        return R.layout.fragment_tabs;
    }

    @Override
    void findViews() {
        ViewPager pager = (ViewPager) mRootView.findViewById(R.id.pager);
        pager.setAdapter(new AdapterSlidingTab(getChildFragmentManager()));

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) mRootView.findViewById(R.id.tabs);

        tabs.setViewPager(pager);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}