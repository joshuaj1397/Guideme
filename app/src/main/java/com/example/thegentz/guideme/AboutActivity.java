package com.example.thegentz.guideme;
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;
import android.view.View;



public class AboutActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

    }
}

    TextView textView = (TextView) findViewById(R.id.abouttext);
    textView.setText("Hello");

