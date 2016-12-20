package camera.home.app.camera.Uitls;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Hai on 11/2/2016.
 */
public class ReadInformationPage {

    public static final String getInformationWebPage(String urls) {
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(urls);
            urlConnection = (HttpURLConnection) url.openConnection();
            //urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            InputStream in = urlConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(in);
            StringBuilder stringBuilder = new StringBuilder();
            int data = isr.read();
            while (data != -1) {
                char current = (char) data;
                stringBuilder.append(current);
                data = isr.read();
            }
            String rs = stringBuilder.toString();
            return rs;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }


}
