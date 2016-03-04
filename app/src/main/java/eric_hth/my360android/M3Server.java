package eric_hth.my360android;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.okhttp.OkHttpClient;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

/**
 * Created by eric_360 on 01/03/16.
 */
public class M3Server {
    // MARK: public
    public static void getUsers(final String token, final Callback<Map<String,M3UserWidgets>> callback){
        final M3ObjectInterface userServerInterface = m3ObjectInterface();
        userServerInterface.getUsersId(token).enqueue(new Callback<ArrayList<M3Object>>() {
            @Override
            public void onResponse(Response<ArrayList<M3Object>> response, Retrofit retrofit) {
                ArrayList<String> list = new ArrayList<String>();
                for(M3Object  object : response.body() ){
                    list.add(object.id);
                }
                userServerInterface.getUsers(list,token).enqueue(new Callback<Map<String,M3UserWidgets>>() {
                    @Override
                    public void onResponse(Response<Map<String,M3UserWidgets>> response, Retrofit retrofit) {
                        callback.onResponse(response,retrofit);
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
    public static void getMe(String token, final Callback<M3User> callback){
        final M3ObjectInterface userServerInterface = m3ObjectInterface();
        Log.d("Warning", "Singleton");
        userServerInterface.getUserMe(token).enqueue(new Callback<M3User>() {
            @Override
            public void onResponse(Response<M3User> response, Retrofit retrofit) {
                callback.onResponse(response,retrofit);
            }
            @Override
            public void onFailure(Throwable t) {
                callback.onFailure(t);
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
    public static class Login {
        interface LoginCompletion {
            public void done(String token,LoggingError error);
            public void error(Throwable t);
        }
        public static void login(final String email, final String pwd, final LoginCompletion completion){
            logInRetrofit().login(email,md5(pwd),true).enqueue(new Callback<LogInRetrofitParser>() {
                @Override
                public void onResponse(retrofit.Response<LogInRetrofitParser> responseCall, Retrofit retrofit) {
                    completion.done(responseCall.body().getToken(), responseCall.body().getError());
                }

                @Override
                public void onFailure(Throwable t) {
                    completion.error(t);
                }
            });
        }
        private static final String md5(final String s) {
            final String MD5 = "MD5";
            try {
                MessageDigest digest = java.security.MessageDigest
                        .getInstance(MD5);
                digest.update(s.getBytes());
                byte messageDigest[] = digest.digest();
                StringBuilder hexString = new StringBuilder();
                for (byte aMessageDigest : messageDigest) {
                    String h = Integer.toHexString(0xFF & aMessageDigest);
                    while (h.length() < 2)
                        h = "0" + h;
                    hexString.append(h);
                }
                return hexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return "";
        }
        private static LogInRetrofit logInRetrofit(){
            Retrofit retrofitNoToken = new Retrofit.Builder()
                    .baseUrl("http://app.360mooc.com")
                    .client(new OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create((new GsonBuilder()).create()))
                    .build();
            return retrofitNoToken.create(Login.LogInRetrofit.class);
        }
        // M3Url.url("/api/users/me?token=%@")
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
    }
    // MARK: public var
    public enum LoggingError {
        @SerializedName("param_password_needed")
        PARAM_PASSWORD_NEEDED,
        @SerializedName("wrong_password")
        WRONG_PASSWORD,
        @SerializedName("param_login_needed")
        PARAM_LOGIN_NEEDED,
        @SerializedName("not_authorized_ip")
        NOT_AUTHORIZED_IP,
        @SerializedName("wrong_company_id")
        WRONG_COMPANY_ID,
        @SerializedName("wrong_ip")
        WRONG_IP,
        @SerializedName("wrong_signature")
        WRONG_SIGNATURE,
    }
}
