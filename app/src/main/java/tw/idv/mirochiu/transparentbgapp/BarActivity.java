package tw.idv.mirochiu.transparentbgapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView t = findViewById(R.id.textView);
        t.setText(this.getClass().getSimpleName());

        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button b2 = findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        (BarActivity.this instanceof NoBarActivity)?
                        new Intent(BarActivity.this,
                            BarActivity.class):
                        new Intent(BarActivity.this,
                                NoBarActivity.class)
                );
                finish();
            }
        });

        WebView webview = (WebView) findViewById(R.id.web_view);
        webview.setBackgroundColor(Color.TRANSPARENT);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCachePath(getDir("svc-appcache", 0).getPath());
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        try {
            InputStream in = getAssets().open("demo.html");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder htmlBuilder = new StringBuilder();
            String str;
            while ((str = reader.readLine()) != null) {
                htmlBuilder.append(str);
            }
            in.close();
            // Chinese text error
            //webview.loadData(htmlBuilder.toString(), "text/html", "UTF-8");
            // Chinese text okay
            webview.loadDataWithBaseURL("", htmlBuilder.toString(), "text/html", "UTF-8",null);
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), String.format("Error:%s",e.getMessage()), Toast.LENGTH_SHORT);
            e.printStackTrace();
        }
    }
}
