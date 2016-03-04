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
        final M3ObjectInterface userServerInterface = m3ObjectInterface();
        userServerInterface.getUsersId(getIntent().getExtras().getString("token")).enqueue(new Callback<ArrayList<M3Object>>() {
            @Override
            public void onResponse(Response<ArrayList<M3Object>> response, Retrofit retrofit) {
                ArrayList<String> list = new ArrayList<String>();
                for(M3Object  object : response.body() ){
                    list.add(object.id);
                }
                userServerInterface.getUsers(list,getIntent().getExtras().getString("token")).enqueue(new Callback<Map<String,M3UserWidgets>>() {
                    @Override
                    public void onResponse(Response<Map<String,M3UserWidgets>> response, Retrofit retrofit) {
                        List<M3UserWidgets> users = new ArrayList<M3UserWidgets>(response.body().values());
                        for(M3UserWidgets  object : users ){
                            recyclerView.loadData(users);
                        }
                    }
                    @Override
                    public void onFailure(Throwable t) {
                    }
                });
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });
        userServerInterface.getUserMe(getIntent().getExtras().getString("token")).enqueue(new Callback<M3User>() {
            @Override
            public void onResponse(Response<M3User> response, Retrofit retrofit) {
                Log.d("Test Get Me",response.body().companies.get(0));
            }
            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
    private static M3ObjectInterface m3ObjectInterface(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://app.360mooc.com")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create((new GsonBuilder()).create()))
                .build();
        return retrofit.create(M3ObjectInterface.class);
    }
    private static interface M3ObjectInterface {
        @GET("/api/users/")
        Call<ArrayList<M3Object>> getUsersId(@Query("token") String token);
        @GET("/api/home/")
        Call<M3HomeObject> getHome(@Query("token") String token);
        @GET("/api/users/me")
        Call<M3User> getUserMe(@Query("token") String token);
        @FormUrlEncoded
        @POST("/api/users/widgets")
        Call<Map<String,M3UserWidgets>> getUsers(@Field("ids[]") List<String> ids,@Query("token") String token);
    }
    public class M3Object implements Serializable {
        @SerializedName("_id")
        @Expose
        private String id;
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
    }
    public class M3UserWidgets implements Serializable {
        @SerializedName("_id")
        @Expose
        private String id;
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        @SerializedName("name")
        @Expose
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
    public class M3User extends M3UserWidgets{
        @SerializedName("companies")
        @Expose
        private ArrayList<String> companies;
        public ArrayList<String> getCompanies() {
            return companies;
        }
        public void setCompanies(ArrayList<String> companies) {
            this.companies = companies;
        }
    }
    public class M3HomeObject implements Serializable {
        @SerializedName("staff")
        @Expose
        private ArrayList<M3Object> users;
        public ArrayList<M3Object> getUsers() {
            return users;
        }
        public void setUsers(ArrayList<M3Object> users) {
            this.users = users;
        }
    }
}