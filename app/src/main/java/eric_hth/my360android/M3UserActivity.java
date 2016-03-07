package eric_hth.my360android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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
    private M3UserRecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m3user);
        recyclerView = (M3UserRecyclerView) findViewById(R.id.recycler);
        M3Server.getUsers(getIntent().getExtras().getString("token"), new Callback<Map<String, M3Server.M3UserWidgets>>() {
            @Override
            public void onResponse(Response<Map<String, M3Server.M3UserWidgets>> response, Retrofit retrofit) {
                List<M3Server.M3UserWidgets> users = new ArrayList<M3Server.M3UserWidgets>(response.body().values());
                recyclerView.loadData(users);
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
    }
}