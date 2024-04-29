package com.example.yasnecovfi11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private EditText editText;
    private Button enter_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView  = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebViewClient(new WebViewClient()); //Помогает приложению открывать ссылки внутри WebView, а не во внешнем браузере
        webView.getSettings().setJavaScriptEnabled(true); //Включаем поддержку JavaScript

        enter_button = findViewById(R.id.enterBtn);
        editText = findViewById(R.id.editTextText);
        enter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editText.getText().toString();
                webView.loadUrl(url); //Загрузка страницы
            }
        });
    }
}