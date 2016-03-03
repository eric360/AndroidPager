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
        usersCall.getAllUsersId(getIntent().getExtras().getString("token")).enqueue(new Callback<ArrayList<IdObject>>() {
            @Override
            public void onResponse(Response<ArrayList<IdObject>> response, Retrofit retrofit) {
                for (IdObject user : response.body()) {
                    Log.d("Test",user.id);
                }
            }
            @Override
            public void onFailure(Throwable t) {

            }
        });

    }
    private static interface UserRetrofit {
        @GET("/api/users/")
        Call<ArrayList<IdObject>> getAllUsersId(@Query("token") String token);
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