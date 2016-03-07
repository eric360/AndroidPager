package eric_hth.my360android;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class M3UserRecyclerFragment extends Fragment {
    private M3UserRecyclerView recycler;
    public M3UserRecyclerFragment(){
    }
    public static M3UserRecyclerFragment newInstance(){
        M3UserRecyclerFragment fragment = new M3UserRecyclerFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_m3_user_recycler, container, false);
        recycler = (M3UserRecyclerView) view.findViewById(R.id.recycler);
        return view;
    }
}
