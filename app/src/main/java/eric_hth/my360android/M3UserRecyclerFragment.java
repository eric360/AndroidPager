package eric_hth.my360android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class M3UserRecyclerFragment extends Fragment {
    private M3UserRecyclerView recycler;
    public M3UserRecyclerFragment(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.m3user_fragment, container, false);
        recycler = (M3UserRecyclerView) view.findViewById(R.id.recycler);
        M3Server.getUsers(getActivity().getIntent().getExtras().getString("token"), new Callback<Map<String, M3Server.M3UserWidgets>>() {
            @Override
            public void onResponse(Response<Map<String, M3Server.M3UserWidgets>> response, Retrofit retrofit) {
                List<M3Server.M3UserWidgets> users = new ArrayList<M3Server.M3UserWidgets>(response.body().values());
                recycler.loadData(users);
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });
        return view;
    }
}
