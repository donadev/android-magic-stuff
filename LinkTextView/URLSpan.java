package com.donadev.androidmagicstuff;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by donadev on 30/06/15.
 */

class URLSpan extends ClickableSpan {
    private String url;
    private Activity activity;
    public URLSpan(String url, Activity activity) {
        this.url = url;
        this.activity = activity;
    }

    @Override
    public void onClick(View widget) {
        Intent i = new Intent(Intent.ACTION_VIEW,
                Uri.parse(url));
        activity.startActivity(i);
    }
}
