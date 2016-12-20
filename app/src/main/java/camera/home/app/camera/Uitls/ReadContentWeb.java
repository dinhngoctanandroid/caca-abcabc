package camera.home.app.camera.Uitls;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import camera.home.app.camera.R;

/**
 * Created by Hai on 11/2/2016.
 */
public class ReadContentWeb extends AsyncTask<String, String, String> {

    private ProgressDialog mProgressDialog;
    private Activity content;
    private boolean isPrint;

    //private static Toast toast;

    public ReadContentWeb(Activity content, boolean isPrint) {
        this.content = content;
        this.isPrint = isPrint;

        //this.toast = new Toast(content);
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog = new ProgressDialog(content,R.style.CustomDialog);
        mProgressDialog.setMessage(content.getString(R.string.wait));
        mProgressDialog.show();
    }

    @Override
    protected String doInBackground(final String... params) {
        final String contentOfWeb;
        contentOfWeb = ReadInformationPage.getInformationWebPage(params[0]);
        return contentOfWeb;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(s!= null)
        {
            if(isPrint)
            {
                InformationWebPage.CONTENT = s;

            }else {
                //Toast.makeText(content,s,Toast.LENGTH_SHORT).show();
                //toast.cancel();
                Toast.makeText(content,s,Toast.LENGTH_SHORT).show();
                //int a = toast.getDuration();
                //a = 7;

            }
            mProgressDialog.dismiss();
        }
    }
    

}
