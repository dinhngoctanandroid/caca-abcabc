package camera.home.app.camera.settting.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import camera.home.app.camera.R;
import camera.home.app.camera.Uitls.InformationWebPage;
import camera.home.app.camera.Uitls.ReadContentWeb;
import camera.home.app.camera.home.ui.TabHomeActivity;

/**
 * Created by Hai on 11/9/2016.
 */

public class TabSettingActivity extends Fragment {
    private EditText txtIPCamera,txtSubfixCamera,txtIpCapsule,txtSubSub;
    private Button btnSave;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View tabSet = inflater.inflate(R.layout.tab_setting, container, false);
        addControl(tabSet);
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        txtIpCapsule.setText(sharedPref.getString("IP",""));

        if (isNetWorkConnected())
        {
            new ReadContentWeb(getActivity(), true).execute(InformationWebPage.CMD_PRINT);
            addEvent();
        } else {
            Toast.makeText(getActivity(), "Pls turn on connect network!", Toast.LENGTH_SHORT).show();
        }
        return tabSet;
    }

    private void addEvent() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ipCamera = txtIPCamera.getText().toString();
                String ipCapsule =  txtIpCapsule.getText().toString();
                String subfixCamera =  txtSubfixCamera.getText().toString();
                String subSubFix = txtSubSub.getText().toString();
                if(ipCapsule!= null && !ipCapsule.equals(""))
                {
                    new ReadContentWeb(getActivity(), true).execute(InformationWebPage.CAMERA_SUBFIX + ipCapsule);
                    SharedPreferences sharedPref = getActivity().getSharedPreferences("CAP",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("IP",ipCapsule);
                    editor.putString("RTSPLINK","rtsp://" + ipCamera+subfixCamera);
                    editor.commit();
                    Toast.makeText(getActivity(),"Finish",Toast.LENGTH_SHORT).show();
                }
                if(ipCamera!= null && !ipCamera.equals("")){
                    new ReadContentWeb(getActivity(), true).execute(InformationWebPage.IP_CMR + ipCamera);
                }

                if(subfixCamera!= null && !subfixCamera.equals("")){
                    new ReadContentWeb(getActivity(), true).execute(InformationWebPage.CAPSULE_SUBFIX_SELECT + subfixCamera);
                }
                if(subSubFix!= null && !subSubFix.equals(""))
                {
                    new ReadContentWeb(getActivity(), true).execute(InformationWebPage.SUB_SUB + subSubFix);
                }
                new ReadContentWeb(getActivity(), true).execute(InformationWebPage.CMD_PRINT);
            }
        });

    }
    private void addControl(View tabSet) {
        txtIPCamera = (EditText) tabSet.findViewById(R.id.txtIPCamera);
        txtIpCapsule = (EditText) tabSet.findViewById(R.id.txtIpCapsule);
        txtSubfixCamera = (EditText) tabSet.findViewById(R.id.txtSubfixCamera);
        btnSave = (Button) tabSet.findViewById(R.id.btnSave);
        txtSubSub = (EditText) tabSet.findViewById(R.id.txtSubSub);
    }

    private  boolean isNetWorkConnected()
    {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
