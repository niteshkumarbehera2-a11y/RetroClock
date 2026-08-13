package com.nitesh.retroclock;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {

    private TextView timeText;
    private TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(Color.BLACK);
        getWindow().setNavigationBarColor(Color.BLACK);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setBackgroundColor(Color.BLACK);

        timeText = new TextView(this);
        timeText.setTextColor(Color.rgb(120, 255, 120));
        timeText.setTextSize(58);
        timeText.setTypeface(Typeface.MONOSPACE, Typeface.BOLD);
        timeText.setGravity(Gravity.CENTER);

        dateText = new TextView(this);
        dateText.setTextColor(Color.rgb(100, 220, 100));
        dateText.setTextSize(18);
        dateText.setTypeface(Typeface.MONOSPACE);
        dateText.setGravity(Gravity.CENTER);

        layout.addView(timeText);

        layout.addView(
                dateText,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );

        setContentView(layout);

        startClock();
    }

    private void startClock() {

        Runnable clock = new Runnable() {
            @Override
            public void run() {

                Date now = new Date();

                String time = new SimpleDateFormat(
                        "HH:mm:ss",
                        Locale.getDefault()
                ).format(now);

                String date = new SimpleDateFormat(
                        "EEE  dd MMM yyyy",
                        Locale.getDefault()
                ).format(now).toUpperCase(Locale.getDefault());

                timeText.setText(time);
                dateText.setText(date);

                timeText.postDelayed(this, 1000);
            }
        };

        timeText.post(clock);
    }
}
