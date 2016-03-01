package eric_hth.my360android;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class M3PagerActivity extends AppCompatActivity {
    ViewPagerAdapter pagerAdapter;
    ViewPager pager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m3pager);
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(pager);
    }
    public class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new ViewPagerFragment();
            Bundle args = new Bundle();
            args.putInt(ViewPagerFragment.ARG_OBJECT, i + 1);
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public int getCount() {
            return 5;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return "" + (position + 1);
        }
    }
    public static class ViewPagerFragment extends Fragment {
        public static final String ARG_OBJECT = "object";
        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(
                    R.layout.m3pager_fragment, container, false);
            Bundle args = getArguments();
            ((TextView) rootView.findViewById(android.R.id.text1)).setText("" +
                    Integer.toString(args.getInt(ARG_OBJECT)));
            return rootView;
        }
    }
}
