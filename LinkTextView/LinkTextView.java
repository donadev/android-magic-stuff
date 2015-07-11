package com.donadev.androidmagicstuff;

import android.app.Activity;
import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by donadev on 30/06/15.
 */
public class LinkTextView extends TextView {
    private Activity ctx;
    public LinkTextView(Context context) {
        super(context);
        ctx = (Activity) context;
    }

    public LinkTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctx = (Activity) context;
    }

    public LinkTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ctx = (Activity) context;
    }

    public void setUrlText(String text) {
        if(text != null) {
            SpannableString span = urlifyText(text, ctx);
            this.setText(span, BufferType.SPANNABLE);
            this.setClickable(true);
            this.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
    public SpannableString urlifyText(String text) {

        Matcher m = android.util.Patterns.WEB_URL.matcher(text);
        SpannableString span = new SpannableString(text);
        StyleSpan typeFaceSpan = new StyleSpan(Typeface.ITALIC);
        UnderlineSpan underlineSpan = new UnderlineSpan();
        int color = activity.getResources().getColor(android.R.color.blue);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(color);
        while(m.find()) {
            int start = m.start();
            int end = m.end();
            String url = m.group(1);
            span.setSpan(typeFaceSpan, start, end, 0);
            span.setSpan(underlineSpan, start, end, 0);
            span.setSpan(foregroundColorSpan, start, end, 0);
            span.setSpan(new URLSpan(url, ctx), start, end, 0);
        }
        return span;
    }
}
