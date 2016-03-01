package eric_hth.my360android;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

public class M3LoginActivity extends AppCompatActivity {
    EditText textView1;
    EditText textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m3login);
        textView1 = (EditText) findViewById(R.id.textView);
        textView2 = (EditText)findViewById(R.id.textView2);
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
