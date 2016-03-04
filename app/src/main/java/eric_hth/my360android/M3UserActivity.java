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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m3user);
        final UserServerInterface userServerInterface = userServerInterface();
        userServerInterface.getUsersId(getIntent().getExtras().getString("token")).enqueue(new Callback<ArrayList<IdObject>>() {
            @Override
            public void onResponse(Response<ArrayList<IdObject>> response, Retrofit retrofit) {
                ArrayList<String> list = new ArrayList<String>();
                for(IdObject  object : response.body() ){
                    list.add(object.id);
                }
                userServerInterface.getUsers(list,getIntent().getExtras().getString("token")).enqueue(new Callback<Map<String,IdObject>>() {
                    @Override
                    public void onResponse(Response<Map<String,IdObject>> response, Retrofit retrofit) {
                        List<IdObject> list = new ArrayList<IdObject>(response.body().values());
                        for(IdObject  object : list ){
                            Log.d("USER ID", object.id);
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
    }
    private static   UserServerInterface userServerInterface(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://app.360mooc.com")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create((new GsonBuilder()).create()))
                .build();
        return retrofit.create(UserServerInterface.class);
    }
    private static interface UserServerInterface {
        @GET("/api/users/")
        Call<ArrayList<IdObject>> getUsersId(@Query("token") String token);
        @GET("/api/home/")
        Call<HomeObject> getHome(@Query("token") String token);
        @FormUrlEncoded
        @POST("/api/users/widgets")
        Call<Map<String,IdObject>> getUsers(@Field("ids[]") List<String> ids,@Query("token") String token);
    }
    public class IdObject implements Serializable {
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
    public class HomeObject implements Serializable {
        @SerializedName("staff")
        @Expose
        private ArrayList<Users> users;
        public ArrayList<Users> getUsers() {
            return users;
        }
        public void setUsers(ArrayList<Users> users) {
            this.users = users;
        }
        public class Users {
            @SerializedName("_id")
            @Expose
            private String id;
            public String getRows() {
                return id;
            }
            public void setRows(String rows) {
                this.id = id;
            }
        }
    }
}