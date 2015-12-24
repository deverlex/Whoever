package vn.whoever;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by spider man on 12/22/2015.
 * TODO: login and logout application
 */
public class StartActivity extends Activity {

    public static final String PREFERENCES = "PREFERENCES_LOCAL";
    public static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.start_layout);

        sharedPreferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        boolean isLogin = sharedPreferences.getBoolean("isLogin", false);
        if(isLogin) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        TextView logoText = (TextView) findViewById(R.id.logoTextStart);
        Typeface bauhau93_font = Typeface.createFromAsset(getAssets(),  "fonts/bauhau93.ttf");
        logoText.setTypeface(bauhau93_font);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
