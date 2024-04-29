package com.example.yasnecovfi11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView  = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebViewClient(new WebViewClient()); //Помогает приложению открывать ссылки внутри WebView, а не во внешнем браузере
        webView.getSettings().setJavaScriptEnabled(true); //Включаем поддержку JavaScript

        editText = findViewById(R.id.editTextText);
        String url = editText.getText().toString();
        webView.loadUrl(url); //Загрузка страницы
    }
}