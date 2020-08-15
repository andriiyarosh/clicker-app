package com.github.cr9ck.clickerapp.presentation.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.cr9ck.clickerapp.databinding.FragmentWebPageBinding;

@SuppressLint("SetJavaScriptEnabled")
public class WebPageFragment extends Fragment {

    private FragmentWebPageBinding binding;
    public static final String URL = "http://html5test.com/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWebPageBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initWebView();
    }

    private void initWebView() {
        CookieManager.getInstance().setAcceptCookie(true);
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });
        binding.webView.loadUrl(URL);
        binding.webView.setOnKeyListener((view, i, keyEvent) -> {
            if (i == KeyEvent.KEYCODE_BACK
                    && keyEvent.getAction() == MotionEvent.ACTION_UP
                    && binding.webView.canGoBack()) {
                binding.webView.goBack();
                return true;
            }
            return false;
        });
    }
}
