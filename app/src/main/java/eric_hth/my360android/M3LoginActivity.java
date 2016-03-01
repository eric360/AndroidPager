package eric_hth.my360android;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.okhttp.OkHttpClient;

import java.io.Serializable;


import retrofit.GsonConverterFactory;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Retrofit;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;



public class M3LoginActivity extends AppCompatActivity {
    TokenInterface apiToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m3_login);
        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeTypeConverter());
//        gsonBuilder.registerTypeAdapter(CourseElement.class, new CourseElementTypeConverter());
//        gsonBuilder.registerTypeAdapter(QuestionAnswerListItem.class, new QuestionAnswerListTypeConverter());
//        gsonBuilder.registerTypeAdapter(CollectedAnswers.class, new CollectedAnswerTypeConverter());
//        gsonBuilder.registerTypeAdapter(FeedItemV2.class, new NewsFeedTypeConverter());
        Gson gson = gsonBuilder.create();

        OkHttpClient clientNoToken = new OkHttpClient();
        Retrofit retrofitNoToken = new Retrofit.Builder()
                .baseUrl("http://app.360mooc.com")
                .client(clientNoToken)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiToken = retrofitNoToken.create(TokenInterface.class);
        attemptLogin("eric.test@360learning.com","eric.test@360learning.com");
    }
    private void attemptLogin(final String email, final String pwd){
        final Call<TokenResponse> call = apiToken.postAuth(email,pwd,true);
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(retrofit.Response<TokenResponse> responseCall, Retrofit retrofit) {
                Log.d("TEST",responseCall.body().toString());
/*                final TokenResponse response = responseCall.body();
                if (response.getToken() != null) {

                    SharedPreferences.Editor edit = mSharedPrefs.edit();
                    edit.putString(AppConstants.SP_AUTH_TOKEN, response.getToken());

                    //edit.putString(AppConstants.SP_AUTH_EMAIL, email);
                    edit.putBoolean(AppConstants.SP_REMEMBER, rememberMe);
                    edit.putString(AppConstants.SP_AUTH_PWDMD5, pwd);

                    edit.apply();

                    Intent intent = new Intent(LoginActivity.this, BootLoadingActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    showProgress(false);
                    mPasswordView.setText("");
                    switch (response.getError()) {
                        case NOT_AUTHORIZED_IP:
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage("Vous n'etes pas authorisé à acceder à cette plateforme depuis cette IP");
                            builder.create().show();
                        case PARAM_PASSWORD_NEEDED:
                        case WRONG_PASSWORD:
                            mPasswordView.requestFocus();
                            mMd5Pwd = null;
                            mPasswordView.setError(getError(response.getError()));
                            SharedPreferences.Editor edit = mSharedPrefs.edit();
                            edit.putString(AppConstants.SP_AUTH_PWDMD5, null);
                            edit.apply();
                            break;
                        case PARAM_LOGIN_NEEDED:
                            mEmailView.requestFocus();
                            mEmailView.setError(getError(response.getError()));
                        default:
                            break;

                    }
                }*/
            }

            @Override
            public void onFailure(Throwable t) {
                //IO Exception
//                t.printStackTrace();
//                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                builder.setMessage("votre connection semble insuffisante, appuyer sur Ok pour réessayer");
//                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        attemptLogin(email, pwd);
//                    }
//                });
//                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                    @Override
//                    public void onDismiss(DialogInterface dialog) {
//                        attemptLogin(email, pwd);
//                    }
//                });
//                builder.create();
//                builder.show();


            }
        });
    }
}
