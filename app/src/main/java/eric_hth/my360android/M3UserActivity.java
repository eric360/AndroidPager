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
        Log.d("Token", getIntent().getExtras().getString("token"));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://app.360mooc.com")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create((new GsonBuilder()).create()))
                .build();
        UserRetrofit usersCall = retrofit.create(UserRetrofit.class);
        usersCall.testGet(getIntent().getExtras().getString("token")).enqueue(new Callback<HomeObject>() {
            @Override
            public void onResponse(Response<HomeObject> response, Retrofit retrofit) {
                Log.d("Test", response.body().users.toString());
            }
            @Override
            public void onFailure(Throwable t) {

            }
        });

    }
    private static interface UserRetrofit {
        @GET("/api/users/")
        Call<ArrayList<IdObject>> getAllUsersId(@Query("token") String token);
        @GET("/api/home/")
        Call<HomeObject> testGet(@Query("token") String token);
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
//    public class HomeObject implements Serializable {
//        @SerializedName("users")
//        @Expose
//        private Users users;
//        public Users getUsers() {
//            return users;
//        }
//        public void setUsers(Users users) {
//            this.users = users;
//        }
//        public class Users {
//            @SerializedName("rows")
//            @Expose
//            private ArrayList<String> rows;
//            public ArrayList<String> getRows() {
//                return rows;
//            }
//            public void setRows(ArrayList<String> rows) {
//                this.rows = rows;
//            }
//        }
//    }
}


//    logInRetrofit().login(email,md5(pwd),true).enqueue(new Callback<LogInRetrofitParser>() {
//@Override
//public void onResponse(retrofit.Response<LogInRetrofitParser> responseCall, Retrofit retrofit) {
//        completion.done(responseCall.body().getToken(), responseCall.body().getError());
//        }
//
//@Override
//public void onFailure(Throwable t) {
//        completion.error(t);
//        }
//        });

//    @GET("/api/users/")
//    Call<ArrayList<IdObject>> getAllUsersId();

//"/api/users/me?token=%@"

/*
private static LogInRetrofit logInRetrofit(){
    Retrofit retrofitNoToken = new Retrofit.Builder()
            .baseUrl("http://app.360mooc.com")
            .client(new OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create((new GsonBuilder()).create()))
            .build();
    return retrofitNoToken.create(Login.LogInRetrofit.class);
}
private static interface LogInRetrofit {
    @FormUrlEncoded
    @POST("/api/tokens")
    Call<LogInRetrofitParser> login(@Field("login") String login, @Field("password") String md5Pwd, @Field("mobile") boolean mobile);
}
private class LogInRetrofitParser implements Serializable {
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("error")
    @Expose
    private LoggingError error;
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public LoggingError getError() {
        return error;
    }
    public void setError(LoggingError error) {
        this.error = error;
    }
}
*/