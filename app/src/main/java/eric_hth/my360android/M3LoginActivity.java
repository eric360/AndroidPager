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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m3_login);
        M3Server.Login.login("eric.test@360learning.com", "eric.test@360learning.com", new M3Server.Login.LoginCompletion() {
            @Override
            public void done(String token, M3Server.LoggingError error) {
                Log.d("TEST NEW", token);
            }
            @Override
            public void error(Throwable t) {
                Log.d("TEST NEW", t.toString());
            }
        });
    }

}
