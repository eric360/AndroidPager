package eric_hth.my360android;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.okhttp.OkHttpClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public class M3UserActivity extends AppCompatActivity {
    ViewPagerAdapter pagerAdapter;
    ViewPager pager;
    TabLayout tabLayout;
    M3UserRecyclerFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m3user);
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(pager);
        fragment = new M3UserRecyclerFragment();

        setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));
        getSupportActionBar().setTitle("Stack");
    }
    public class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int i) {
            if(i == 1){
                return fragment;
            }
            Fragment fragment = new ViewPagerFragment();
            Bundle args = new Bundle();
            args.putInt(ViewPagerFragment.ARG_OBJECT, i + 1);
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public int getCount() {
            return 2;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            if(position == 0){
                return "Home";
            }
            else if(position == 1){
                return "Users";
            }
            return "";
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.m3user_menu, menu);
        return true;
    }

//    protected void loadFragment(){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        Fragment fragment = fragmentManager.findFragmentByTag("post");
//        if(fragment==null) {
//            fragment = new M3UserRecyclerFragment();
//        }
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.fragment, fragment, "post");
//        transaction.commit();
//    }
}