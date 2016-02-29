package eric_hth.my360android;

import android.app.ActionBar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class M3LoginActivity extends AppCompatActivity {
    TheAdapter_FragmentStatePager adapter_fragmentStatePager;
    ViewPager pager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m3loginactivity);
        adapter_fragmentStatePager = new TheAdapter_FragmentStatePager(getSupportFragmentManager());
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter_fragmentStatePager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(pager);
        tabLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("test","click");
            }
        });
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                Log.d("Test", "Yo");
//                pager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }
    // Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
    public class TheAdapter_FragmentStatePager extends FragmentStatePagerAdapter {
        public TheAdapter_FragmentStatePager(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new TheAdapter_Fragment();
            Bundle args = new Bundle();
            args.putInt(TheAdapter_Fragment.ARG_OBJECT, i + 1);
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
    public static class TheAdapter_Fragment extends Fragment {
        public static final String ARG_OBJECT = "object";
        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            // The last two arguments ensure LayoutParams are inflated
            // properly.
            View rootView = inflater.inflate(
                    R.layout.fragment_collection_object, container, false);
            Bundle args = getArguments();
            ((TextView) rootView.findViewById(android.R.id.text1)).setText("" +
                    Integer.toString(args.getInt(ARG_OBJECT)));
            return rootView;
        }
    }
}
