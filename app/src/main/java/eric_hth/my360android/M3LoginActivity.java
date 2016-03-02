package eric_hth.my360android;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.vstechlab.easyfonts.EasyFonts;
public class M3LoginActivity extends AppCompatActivity {
    private TextView title;
    private ShimmerFrameLayout shimmer;
    private EditText editView1;
    private EditText editView2;
    private FloatingActionButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m3login);
        shimmer = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        shimmer.setDuration(1000);
        shimmer.startShimmerAnimation();
        editView1 = (EditText) findViewById(R.id.textView);
        editView1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                shimmer.stopShimmerAnimation();
            }
        });
        editView2 = (EditText)findViewById(R.id.textView2);
        editView2.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    editView2.clearFocus();
                    return true;
                }
                return false;
            }
        });
        editView2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                shimmer.stopShimmerAnimation();
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }

            public void hideKeyboard(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
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
        button = (FloatingActionButton) findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
