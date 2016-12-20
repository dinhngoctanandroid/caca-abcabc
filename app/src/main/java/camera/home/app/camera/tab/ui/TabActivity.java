package camera.home.app.camera.tab.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import camera.home.app.camera.R;
import camera.home.app.camera.Uitls.InformationWebPage;

public class TabActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private AdapterTab mAdapterTab;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        SharedPreferences sharedPref = getSharedPreferences("CAP", Context.MODE_PRIVATE);
        InformationWebPage.IP = sharedPref.getString("IP", "");
        String id = sharedPref.getString("IP", "");
        Log.d("PRINT_CAPSULE", InformationWebPage.IP);

        if (InformationWebPage.IP.equals("")) {
            InformationWebPage.IP = "192.168.2.103";
            id = "192.168.2.103";

            String print = InformationWebPage.HTTP + id + InformationWebPage.PRINT_CAPSULE;
            InformationWebPage.CMD_PRINT = print;

            String ip_camera = InformationWebPage.HTTP + id + InformationWebPage.IP_CAMERA;
            InformationWebPage.IP_CMR = ip_camera;

            String modeOn = InformationWebPage.HTTP + id + InformationWebPage.CAP_ON;
            String modeOff = InformationWebPage.HTTP + id + InformationWebPage.CAP_OFF;
            InformationWebPage.CAPSULE_ON = modeOn;
            InformationWebPage.CAPSULE_OFF = modeOff;
            String capSub = InformationWebPage.HTTP + id + InformationWebPage.IP_SUBFIX;
            InformationWebPage.CAPSULE_SUBFIX_SELECT = capSub;

            String mode = InformationWebPage.HTTP + id + InformationWebPage.IP_MODE;
            InformationWebPage.CAPSULE_MODE_SELECT = mode;

            String subSubFix = InformationWebPage.HTTP + id + InformationWebPage.SUB;
            InformationWebPage.SUB_SUB = subSubFix;
        } else {
            String print = InformationWebPage.HTTP + id + InformationWebPage.PRINT_CAPSULE;
            InformationWebPage.CMD_PRINT = print;

            String ip_camera = InformationWebPage.HTTP + id + InformationWebPage.IP_CAMERA;
            InformationWebPage.IP_CMR = ip_camera;

            String modeOn = InformationWebPage.HTTP + id + InformationWebPage.CAP_ON;
            String modeOff = InformationWebPage.HTTP + id + InformationWebPage.CAP_OFF;
            InformationWebPage.CAPSULE_ON = modeOn;
            InformationWebPage.CAPSULE_OFF = modeOff;

            String capSub = InformationWebPage.HTTP + id + InformationWebPage.IP_SUBFIX;
            InformationWebPage.CAPSULE_SUBFIX_SELECT = capSub;

            String mode = InformationWebPage.HTTP + id + InformationWebPage.IP_MODE;
            InformationWebPage.CAPSULE_MODE_SELECT = mode;

            String subSubFix = InformationWebPage.HTTP + id + InformationWebPage.SUB;
            InformationWebPage.SUB_SUB = subSubFix;
        }
        processTab();
    }

    private void processTab() {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        final TabLayout.Tab home = mTabLayout.newTab();
        final TabLayout.Tab setting = mTabLayout.newTab();
        final TabLayout.Tab about = mTabLayout.newTab();

        View homeView = getLayoutInflater().inflate(R.layout.tab_home, null);
        View setView = getLayoutInflater().inflate(R.layout.tab_setting, null);
        View setAbout = getLayoutInflater().inflate(R.layout.tab_about, null);

        home.setCustomView(homeView);
        setting.setCustomView(setView);
        about.setCustomView(setAbout);

        mTabLayout.addTab(home, 0);
        mTabLayout.addTab(setting, 1);
        mTabLayout.addTab(about, 2);

        mAdapterTab = new AdapterTab(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapterTab);
        mViewPager.setCurrentItem(mAdapterTab.getMiddlePosition(), false);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("Adapter", "View pager onPageSelected = " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
