package org.metatrans.commons.web;


import android.view.View;
import android.webkit.WebView;


public class Activity_WebView_StatePreservingImpl_With_VideoPlayer extends Activity_WebView_StatePreservingImpl {
	
	
	@Override
	protected WebView createWebView() {
		return new MyWebView(this);
	}
	
	
	@Override
	protected View getViewToAttach() {
		MyWebView webView = (MyWebView)getWebView();
		return webView.getLayout();
	}
	
	
	@Override
	protected boolean handleCustomView() {
		
		MyWebView webView = (MyWebView)getWebView();
		
        if (webView.inCustomView()) {
        	webView.hideCustomView();
            return true;
        }
        
        return false;
	}
}
