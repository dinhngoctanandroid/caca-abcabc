package camera.home.app.camera.home.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Config;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import camera.home.app.camera.R;
import camera.home.app.camera.Uitls.InformationWebPage;
import camera.home.app.camera.Uitls.ReadContentWeb;
import camera.home.app.camera.Uitls.ReadInformationPage;

/**
 * Created by Hai on 11/9/2016.
 */

public class TabHomeActivity extends Fragment {
    private boolean ischeckCB = true;
    private VideoView videoView;
    private String videoRtspUrl = "rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov";
    private String m = "rtmp://rrbalancer.broadcast.tneg.de:1935/pw/ruk/ruk";
    private String demo = "rtsp://192.168.2.106/ch0_0.h264";
    private String demo2 = "rtsp://192.168.2.64";
    private Switch swtTurnOff;
    private CheckBox chbMod1, chbMod2, chbMod3, chbMod4;
    private TextView txtMode1, txtMode2, txtMode3, txtMode4;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View tabHome = inflater.inflate(R.layout.tab_home, container, false);
        addControl(tabHome);
        if (isNetWorkConnected()) {
            new ReadContentWeb(getActivity(), true).execute(InformationWebPage.CMD_PRINT);
            //// on the NetworkOnMainThreadException for more information.
            //// It explains why this occurs on Android 3.0 and above..
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            /// we add 2 line when we want use the urlconnection in thread main UI
            String testPrint = ReadInformationPage.getInformationWebPage(InformationWebPage.HTTP + InformationWebPage.IP + InformationWebPage.PRINT_CAPSULE);
            InformationWebPage.CONTENT = testPrint;
            if (!testPrint.equals("")) {
                addEvent();
            } else {
                setDisableView();
            }
        } else {
            setDisableView();
            Toast.makeText(getActivity(), "Pls turn on connect network!", Toast.LENGTH_SHORT).show();
        }
        return tabHome;
    }

    private void addControl(View tabHome) {
        swtTurnOff = (Switch) tabHome.findViewById(R.id.swtTurnOff);
        chbMod1 = (CheckBox) tabHome.findViewById(R.id.chbMod1);
        chbMod2 = (CheckBox) tabHome.findViewById(R.id.chbMod2);
        chbMod3 = (CheckBox) tabHome.findViewById(R.id.chbMod3);
        chbMod4 = (CheckBox) tabHome.findViewById(R.id.chbMod4);

        txtMode1 = (TextView) tabHome.findViewById(R.id.txtMode1);
        txtMode2 = (TextView) tabHome.findViewById(R.id.txtMode2);
        txtMode3 = (TextView) tabHome.findViewById(R.id.txtMode3);
        txtMode4 = (TextView) tabHome.findViewById(R.id.txtMode4);
        progressBar = (ProgressBar) tabHome.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        videoView = (VideoView) tabHome.findViewById(R.id.videoView);
    }

    private void testCheck(int i, boolean isExist) {
        if (isExist) {
            if (i == 1) {
                chbMod1.setChecked(true);
                chbMod2.setChecked(false);
                chbMod3.setChecked(false);
                chbMod4.setChecked(false);

                txtMode1.setTextColor(getResources().getColor(R.color.gray2));
                txtMode2.setTextColor(getResources().getColor(R.color.gray2));
                txtMode3.setTextColor(getResources().getColor(R.color.gray2));
                txtMode4.setTextColor(getResources().getColor(R.color.gray2));

                new ReadContentWeb(getActivity(), false).execute(InformationWebPage.CAPSULE_MODE_SELECT + 1);
                new ReadContentWeb(getActivity(), true).execute(InformationWebPage.CMD_PRINT);

            } else if (i == 2) {
                chbMod1.setChecked(false);
                chbMod2.setChecked(true);
                chbMod3.setChecked(false);
                chbMod4.setChecked(false);

                txtMode1.setTextColor(getResources().getColor(R.color.gray2));
                txtMode2.setTextColor(getResources().getColor(R.color.gray2));
                txtMode3.setTextColor(getResources().getColor(R.color.gray2));
                txtMode4.setTextColor(getResources().getColor(R.color.gray2));

                new ReadContentWeb(getActivity(), false).execute(InformationWebPage.CAPSULE_MODE_SELECT + 2);
                new ReadContentWeb(getActivity(), true).execute(InformationWebPage.CMD_PRINT);
            } else if (i == 3) {
                chbMod1.setChecked(false);
                chbMod2.setChecked(false);
                chbMod3.setChecked(true);
                chbMod4.setChecked(false);

                txtMode1.setTextColor(getResources().getColor(R.color.gray2));
                txtMode2.setTextColor(getResources().getColor(R.color.gray2));
                txtMode3.setTextColor(getResources().getColor(R.color.gray2));
                txtMode4.setTextColor(getResources().getColor(R.color.gray2));

                new ReadContentWeb(getActivity(), false).execute(InformationWebPage.CAPSULE_MODE_SELECT + 3);
                new ReadContentWeb(getActivity(), true).execute(InformationWebPage.CMD_PRINT);
            } else {
                chbMod1.setChecked(false);
                chbMod2.setChecked(false);
                chbMod3.setChecked(false);
                chbMod4.setChecked(true);

                txtMode1.setTextColor(getResources().getColor(R.color.gray2));
                txtMode2.setTextColor(getResources().getColor(R.color.gray2));
                txtMode3.setTextColor(getResources().getColor(R.color.gray2));
                txtMode4.setTextColor(getResources().getColor(R.color.gray2));

                new ReadContentWeb(getActivity(), false).execute(InformationWebPage.CAPSULE_MODE_SELECT + 4);
                new ReadContentWeb(getActivity(), true).execute(InformationWebPage.CMD_PRINT);
            }
        } else {
            if (i == 1) {
                chbMod1.setChecked(true);
                chbMod2.setChecked(false);
                chbMod3.setChecked(false);
                chbMod4.setChecked(false);

                txtMode1.setTextColor(getResources().getColor(R.color.gray2));
                txtMode2.setTextColor(getResources().getColor(R.color.gray2));
                txtMode3.setTextColor(getResources().getColor(R.color.gray2));
                txtMode4.setTextColor(getResources().getColor(R.color.gray2));
            } else if (i == 2) {
                chbMod1.setChecked(false);
                chbMod2.setChecked(true);
                chbMod3.setChecked(false);
                chbMod4.setChecked(false);

                txtMode1.setTextColor(getResources().getColor(R.color.gray2));
                txtMode2.setTextColor(getResources().getColor(R.color.gray2));
                txtMode3.setTextColor(getResources().getColor(R.color.gray2));
                txtMode4.setTextColor(getResources().getColor(R.color.gray2));
            } else if (i == 3) {
                chbMod1.setChecked(false);
                chbMod2.setChecked(false);
                chbMod3.setChecked(true);
                chbMod4.setChecked(false);

                txtMode1.setTextColor(getResources().getColor(R.color.gray2));
                txtMode2.setTextColor(getResources().getColor(R.color.gray2));
                txtMode3.setTextColor(getResources().getColor(R.color.gray2));
                txtMode4.setTextColor(getResources().getColor(R.color.gray2));
            } else {
                chbMod1.setChecked(false);
                chbMod2.setChecked(false);
                chbMod3.setChecked(false);
                chbMod4.setChecked(true);

                txtMode1.setTextColor(getResources().getColor(R.color.gray2));
                txtMode2.setTextColor(getResources().getColor(R.color.gray2));
                txtMode3.setTextColor(getResources().getColor(R.color.gray2));
                txtMode4.setTextColor(getResources().getColor(R.color.gray2));
            }
        }


    }

    private void addEvent() {


        String link_camera = "";

        try {
            int positionCapsule = InformationWebPage.CONTENT.indexOf(InformationWebPage.CAPSULE_STATUS);
            if (positionCapsule > 0) {
                String getInformationOnOff =
                        InformationWebPage.CONTENT.substring(positionCapsule +
                                        InformationWebPage.CAPSULE_STATUS.length() + 3,
                                positionCapsule + InformationWebPage.CAPSULE_STATUS.length() + 3 + 3);
                if (getInformationOnOff.equals("off")) {
                    swtTurnOff.setChecked(true);
                    ischeckCB = false;
                    Toast.makeText(getActivity(), getString(R.string.stateOff), Toast.LENGTH_SHORT).show();
                } else {
                    ischeckCB = true;
                    swtTurnOff.setChecked(false);
                    Toast.makeText(getActivity(), getString(R.string.stateOn), Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception e) {
        }
        try {
            int positionLink = InformationWebPage.CONTENT.indexOf(InformationWebPage.LINK_CAMERA);
            int positionCamera_ip = InformationWebPage.CONTENT.indexOf("camera_ip");
            if (positionLink > 0) {
                link_camera = InformationWebPage.CONTENT.substring(
                        positionLink + InformationWebPage.LINK_CAMERA.length() + 3, positionCamera_ip - 1);
            }
        } catch (Exception e) {
            link_camera = "";
        }
        try {
            if (link_camera.equals("")) {
                SharedPreferences sharedPref = getActivity().getSharedPreferences("CAP", Context.MODE_PRIVATE);
                link_camera = sharedPref.getString("RTSPLINK", "");
            }
            Uri video = Uri.parse(link_camera);
            videoView.setVideoURI(video);
            videoView.start();
            progressBar.setVisibility(View.VISIBLE);
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(final MediaPlayer mp) {
                    mp.start();
                    mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                        @Override
                        public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i1) {
                            progressBar.setVisibility(View.GONE);
                            mp.start();
                        }
                    });
                }
            });
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                progressBar.setVisibility(View.GONE);

            }
        });


        new ReadContentWebPrint().execute(InformationWebPage.CMD_PRINT);


        swtTurnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    {
                        new ReadContentWeb(getActivity(), false).execute(InformationWebPage.CAPSULE_ON);
                        new ReadContentWeb(getActivity(), true).execute(InformationWebPage.CMD_PRINT);

                    }
                } else {
                    {
                        new ReadContentWeb(getActivity(), false).execute(InformationWebPage.CAPSULE_OFF);
                        new ReadContentWeb(getActivity(), true).execute(InformationWebPage.CMD_PRINT);
                    }
                }

            }
        });

        chbMod1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((CheckBox) v).isChecked()) {
                    testCheck(1, true);
                } else {
                    testCheck(1, false);
                }

            }
        });


        chbMod2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*if (swtTurnOff.isChecked()) {*/
                if (((CheckBox) v).isChecked()) {
                    testCheck(2, true);
                } else {
                    testCheck(2, false);
                }
            }
        });

        chbMod3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((CheckBox) v).isChecked()) {
                    testCheck(3, true);
                } else {
                    testCheck(3, false);
                }
            }
        });

        chbMod4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((CheckBox) v).isChecked()) {
                    testCheck(4, true);
                } else {
                    testCheck(4, false);
                }

            }
        });
        txtMode1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chbMod1.isChecked()) {
                    testCheck(1, false);
                } else {
                    testCheck(1, true);
                }

            }
        });
        txtMode2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (chbMod2.isChecked()) {
                    testCheck(2, false);
                } else {
                    testCheck(2, true);
                }
            }
        });
        txtMode3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (chbMod3.isChecked()) {
                    testCheck(3, false);
                } else {
                    testCheck(3, true);
                }

            }
        });
        txtMode4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chbMod4.isChecked()) {
                    testCheck(4, false);
                } else {
                    testCheck(4, true);
                }

            }
        });
    }

    private void testUnCheck() {
        chbMod1.setChecked(false);
        chbMod2.setChecked(false);
        chbMod3.setChecked(false);
        chbMod4.setChecked(false);
        txtMode1.setTextColor(getResources().getColor(R.color.gray2));
        txtMode2.setTextColor(getResources().getColor(R.color.gray2));
        txtMode3.setTextColor(getResources().getColor(R.color.gray2));
        txtMode4.setTextColor(getResources().getColor(R.color.gray2));
    }

    private void setDisableView() {
        swtTurnOff.setEnabled(false);
        chbMod1.setEnabled(false);
        chbMod2.setEnabled(false);
        chbMod3.setEnabled(false);
        chbMod4.setEnabled(false);
    }

    private void positionCheckbox() {
        String text = InformationWebPage.CONTENT;
        if (!text.equals("") || text != null) {
            int mod;
            int positionMod = text.indexOf(InformationWebPage.CAPSULE_MODE);
            Log.d("CKX", positionMod + "");
            String getInformationMode =
                    text.substring(positionMod + InformationWebPage.CAPSULE_MODE.length() + 3, positionMod + InformationWebPage.CAPSULE_MODE.length() + 4).charAt(0) + "";

            Log.d("CKX", getInformationMode);
            try {
                mod = Integer.parseInt(getInformationMode);
            } catch (NumberFormatException ex) {
                if (Config.DEBUG) ex.printStackTrace();
                return;
            }
            switch (mod) {
                case 1:
                    testCheck(1, false);
                    break;
                case 2:
                    testCheck(2, false);
                    break;
                case 3:
                    testCheck(3, false);
                    break;
                case 4:
                    testCheck(4, false);
                    break;
            }
        }

    }
    private boolean isNetWorkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public class ReadContentWebPrint extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String text = ReadInformationPage.getInformationWebPage(params[0]);
            if (!text.equals("")) {
                publishProgress(text);
            }
            return text;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            if (values.length > 0) {

                int positionCapsule = values[0].indexOf(InformationWebPage.CAPSULE_STATUS);
                String getInformationOnOff =
                        values[0].substring(positionCapsule +
                                        InformationWebPage.CAPSULE_STATUS.length() + 3,
                                positionCapsule + InformationWebPage.CAPSULE_STATUS.length() + 3 + 3);
                if (getInformationOnOff.equals("off")) {
                    swtTurnOff.setChecked(false);
                    ischeckCB = false;

                } else {
                    ischeckCB = true;
                    swtTurnOff.setChecked(true);
                }

                int mod;
                int positionMod = values[0].indexOf(InformationWebPage.CAPSULE_MODE);
                Log.d("CKX", positionMod + "");
                String getInformationMode =
                        values[0].substring(positionMod + InformationWebPage.CAPSULE_MODE.length() + 3, positionMod + InformationWebPage.CAPSULE_MODE.length() + 4).charAt(0) + "";

                Log.d("CKX", getInformationMode);
                try {
                    mod = Integer.parseInt(getInformationMode);
                } catch (NumberFormatException ex) {
                    if (Config.DEBUG) ex.printStackTrace();
                    return;
                }
                switch (mod) {
                    case 1:
                        testCheck(1, false);
                        break;
                    case 2:
                        testCheck(2, false);
                        break;
                    case 3:
                        testCheck(3, false);
                        break;
                    case 4:
                        testCheck(4, false);
                        break;
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                InformationWebPage.CONTENT = s;
            }
        }
    }
}