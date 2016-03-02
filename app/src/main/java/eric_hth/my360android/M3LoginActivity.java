package eric_hth.my360android;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.vstechlab.easyfonts.EasyFonts;

public class M3LoginActivity extends AppCompatActivity {
    EditText textView1;
    EditText textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m3login);
        textView1 = (EditText) findViewById(R.id.textView);
        textView2 = (EditText)findViewById(R.id.textView2);
        ShimmerFrameLayout container =
                (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        container.setDuration(1000);
        container.startShimmerAnimation();
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
        TextView myTextView = (TextView)findViewById(R.id.title);
        myTextView.setTypeface(EasyFonts.robotoThin(this));
    }

}
