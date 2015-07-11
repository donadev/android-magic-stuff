package com.donadev.androidmagicstuff;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import java.util.HashMap;
import java.util.UUID;


/**
 * Created by donadev on 11/07/15.
 * 
 */
public class JSWebView extends WebView {
    private HashMap<String, ValueCallback<String>> cache = new HashMap<>();
    private final String INTERFACE_NAME = "android";
    @JavascriptInterface
    public void onData(String id, String value) {
        ValueCallback<String> cb = cache.get(id);
        cb.onReceiveValue(value);
    }
    public JSWebView(Context context) {
        super(context);
        addInterface();
    }
    private void addInterface() {
        this.addJavascriptInterface(this, INTERFACE_NAME);
    }
    private String generateIdentifier() {
        return UUID.randomUUID().toString();
    }
    private String textForJs(String identifier, String data) {
        return "javascript:" + INTERFACE_NAME + ".onData('" + identifier + "', " + data + ")";
    }
    public JSWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        addInterface();
    }

    public JSWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addInterface();
    }
    @Override
    public void evaluateJavascript(String js, ValueCallback<String> cb) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            // only for kitkat and newer versions
            super.evaluateJavascript(js, cb);
        else {
            String identifier = generateIdentifier();
            cache.put(identifier, cb);
            String formattedJs = textForJs(identifier, js);
            this.loadUrl(formattedJs);
        }
    }
}
