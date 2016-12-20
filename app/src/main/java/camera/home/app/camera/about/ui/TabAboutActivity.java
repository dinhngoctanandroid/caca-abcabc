package camera.home.app.camera.about.ui;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import camera.home.app.camera.R;
import camera.home.app.camera.Uitls.InformationWebPage;
import camera.home.app.camera.Uitls.ReadInformationPage;

/**
 * Created by Hai on 11/16/2016.
 */

public class TabAboutActivity extends Fragment {
    private TextView txtPrintTab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View tabAbout = inflater.inflate(R.layout.tab_about, container, false);
        txtPrintTab = (TextView) tabAbout.findViewById(R.id.txtPrintAbout);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        /// we add 2 line when we want use the urlconnection in thread main UI
        String testPrint = ReadInformationPage.getInformationWebPage(InformationWebPage.HTTP + InformationWebPage.IP + InformationWebPage.PRINT_CAPSULE);
        txtPrintTab.setText(InformationWebPage.CONTENT);
        return tabAbout;
    }
}
