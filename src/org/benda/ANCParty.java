package org.benda;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;

public class ANCParty extends Activity
{

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

        setContentView(R.layout.main);

//        setTitle("Amani National Congress Party of Kenya");
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title);

    }

    /**
     * Called when the user clicks the Send button
     */
    public void openSocialMedia(View view)
    {
        // Do something in response to button
        setContentView(R.layout.connect);
    }

    /**
     * Called when the user clicks the Send button
     */
    public void openFacebook(View view)
    {
        // Do something in response to button
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/udfkenya"));
        startActivity(i);
        finish();
    }

    /**
     * Called when the user clicks the Send button
     */
    public void openTwitter(View view)
    {
        // Do something in response to button
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/UDFParty"));
        startActivity(i);
    }

    /**
     * Called when the user clicks the Send button
     */
    public void openMessages(View view)
    {
        setContentView(R.layout.messages);

        final TextView tv = (TextView) findViewById(R.id.MessagesTextView);

        tv.setMovementMethod(new ScrollingMovementMethod());

        tv.setText(R.string.wait);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            public void run()
            {
//                tv.setText(Html.fromHtml(executeHttpGet("http://en.wikipedia.org/wiki/United_Democratic_Forum_Party")));

                String staticData = "<p>The <b>United Democratic Forum Party (UDFP)</b> "
                        + "is a Kenyan political party founded in 2012, by a cross "
                        + "section of younger members of Kenya's 10th parliament under "
                        + "the premise of steering reforms to Kenya's governance "
                        + "and boosting economic growth. "
                        + "Deputy Prime Minister <a title='Musalia Mudavadi' "
                        + "href='/wiki/Musalia_Mudavadi'>Musalia Mudavadi</a> "
                        + "has expressed interest in becoming the party's presidential "
                        + "candidate in the 2013 Kenyan general election.</p>";

                tv.setText(Html.fromHtml(staticData));
            }
        }, 3000);

    }

    /**
     * Called when the user clicks the Map button
     */
    public void openMap(View view)
    {
//            android:theme="@android:style/Theme.Black"
//              android:theme="@android:style/Theme.DeviceDefault"
//              android:theme="@android:style/Theme.DeviceDefault.Dialog"
//              android:theme="@android:style/Theme.Holo"-->
//              android:theme="@android:style/Theme.Translucent"
    }

    public String executeHttpGet(String URL)
    {
        //This method  for HttpConnection
        BufferedReader bufferedReader = null;
        try
        {
            HttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "android");
            HttpGet request = new HttpGet();
            request.setHeader("Content-Type", "text/plain; charset=utf-8");
            request.setURI(new URI(URL));
            HttpResponse response = client.execute(request);
            bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer stringBuffer = new StringBuffer("");
            String line;

            String NL = System.getProperty("line.separator");
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line).append(NL);
                System.out.print(stringBuffer);
            }
            bufferedReader.close();
            String page = stringBuffer.toString();
            System.out.println(page + "page");
            return page;
        }
        catch (Exception ex)
        {
            return ex.toString();
        }
        finally
        {
            if (bufferedReader != null)
            {
                try
                {
                    bufferedReader.close();
                }
                catch (IOException e)
                {
                    Log.d("ANC Party", e.toString());
                }
            }
        }
    }

    @Override
    public void onBackPressed()
    {
        setContentView(R.layout.main);
    }

    /**
     * Called when the user clicks the Exit button
     */
    public void exitApplication(View view)
    {
        // Do something in response to button
        finish();
    }
}
