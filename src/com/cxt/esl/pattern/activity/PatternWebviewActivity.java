package com.cxt.esl.pattern.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.cxt.esl.R;
import com.cxt.esl.pattern.domain.Pattern;

public class PatternWebviewActivity extends Activity{

	private Pattern pattern;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pattern_webview);
		Intent intent = getIntent();
		pattern = (Pattern) intent.getSerializableExtra("pattern");
		String strHtml = "<html><head><meta http-equiv='content-type' content='text/html; charset=UTF-8'></head><body>"
				+ pattern.getCode() + "</body></html>";
		WebView webView = (WebView)findViewById(R.id.webview_pattern);
		webView.loadDataWithBaseURL("", strHtml, "text/html", "UTF-8", null);
	}

}
