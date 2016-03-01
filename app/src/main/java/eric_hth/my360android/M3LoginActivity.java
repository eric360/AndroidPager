package eric_hth.my360android;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import retrofit.GsonConverterFactory;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Retrofit;
public class M3LoginActivity extends AppCompatActivity {
    TokenInterface apiToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m3_login);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        OkHttpClient clientNoToken = new OkHttpClient();
        Retrofit retrofitNoToken = new Retrofit.Builder()
                .baseUrl("http://app.360mooc.com")
                .client(clientNoToken)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiToken = retrofitNoToken.create(TokenInterface.class);
        attemptLogin("eric.test@360learning.com",Hash.md5("eric.test@360learning.com"));
    }
    private void attemptLogin(final String email, final String pwd){
        final Call<TokenResponse> call = apiToken.postAuth(email,pwd,true);
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(retrofit.Response<TokenResponse> responseCall, Retrofit retrofit) {
                if (responseCall.body().getToken() != null) {
                    Log.d("TEST",responseCall.body().getToken());
                }else{
                    Log.d("TEST","Erreur");
                    Log.d("TEST",responseCall.body().getError().toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("TEST","Failure");
            }
        });
    }
}
