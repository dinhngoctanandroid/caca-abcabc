package camera.home.app.camera.start.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import camera.home.app.camera.R;
import camera.home.app.camera.Uitls.InformationWebPage;
import camera.home.app.camera.Uitls.ReadContentWeb;
import camera.home.app.camera.tab.ui.TabActivity;

public class SplashActivity extends AppCompatActivity {

    private Handler mHandler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPref = getSharedPreferences("CAP",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.commit();
        InformationWebPage.IP = "192.168.2.103";

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, TabActivity.class));
                finish();
            }
        }, 1000);
    }
}
