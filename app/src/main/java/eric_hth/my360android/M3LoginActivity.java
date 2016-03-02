package eric_hth.my360android;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.vstechlab.easyfonts.EasyFonts;

public class M3LoginActivity extends AppCompatActivity {
    private TextView title;
    private ShimmerFrameLayout shimmer;
    private EditText editView1;
    private EditText editView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m3login);
        editView1 = (EditText) findViewById(R.id.textView);
        editView2 = (EditText)findViewById(R.id.textView2);
        shimmer = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        shimmer.setDuration(1000);
        shimmer.startShimmerAnimation();
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
        title = (TextView)findViewById(R.id.title);
        title.setTypeface(EasyFonts.robotoThin(this));
    }

}
