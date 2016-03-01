package eric_hth.my360android;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.okhttp.OkHttpClient;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
/**
 * Created by eric_360 on 01/03/16.
 */
public class M3Server {
    // MARK: public
    public static class Login {
        interface LoginCompletion {
            public void done(String token,LoggingError error);
            public void error(Throwable t);
        }
        public static void login(final String email, final String pwd, final LoginCompletion completion){
            final Call<Login.TokenResponse> call = Login.logInInterface().postAuth(email,Login.MD5.process(pwd),true);
            call.enqueue(new Callback<TokenResponse>() {
                @Override
                public void onResponse(retrofit.Response<Login.TokenResponse> responseCall, Retrofit retrofit) {
                    completion.done(responseCall.body().getToken(),responseCall.body().getError());
                }
                @Override
                public void onFailure(Throwable t) {
                    completion.error(t);
                }
            });
        }
        private static class MD5 {
            public static final String process(final String s) {
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
        }
        private class TokenResponse implements Serializable {
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
        private static TokenInterface logInInterface(){
            Retrofit retrofitNoToken = new Retrofit.Builder()
                    .baseUrl("http://app.360mooc.com")
                    .client(new OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create((new GsonBuilder()).create()))
                    .build();
            return retrofitNoToken.create(Login.TokenInterface.class);
        }
        private static interface TokenInterface {
            @FormUrlEncoded
            @POST("/api/tokens")
            Call<TokenResponse> postAuth(@Field("login") String login, @Field("password") String md5Pwd, @Field("mobile") boolean mobile);
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
