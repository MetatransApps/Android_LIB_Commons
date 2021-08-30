package org.metatrans.commons.web;


import android.webkit.WebChromeClient;


//http://stackoverflow.com/questions/14763483/android-webview-with-an-embedded-youtube-video-full-screen-button-freezes-video/15127046#15127046
//http://stackoverflow.com/questions/3815090/webview-and-html5-video
public class MyWebChromeClient extends WebChromeClient {
	
	
    /*private MyWebChromeClient mWebChromeClient = null;
    private View mCustomView;
    private RelativeLayout mContentView;
    private FrameLayout mCustomViewContainer;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;
    
    private FrameLayout.LayoutParams LayoutParameters = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT);
	
    
    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {
        // if a view already exists then immediately terminate the new one
        if (mCustomView != null) {
            callback.onCustomViewHidden();
            return;
        }
        mContentView = (RelativeLayout) findViewById(R.id.activity_main);
        mContentView.setVisibility(View.GONE);
        mCustomViewContainer = new FrameLayout(MainActivity.this);
        mCustomViewContainer.setLayoutParams(LayoutParameters);
        mCustomViewContainer.setBackgroundResource(android.R.color.black);
        view.setLayoutParams(LayoutParameters);
        mCustomViewContainer.addView(view);
        mCustomView = view;
        mCustomViewCallback = callback;
        mCustomViewContainer.setVisibility(View.VISIBLE);
        setContentView(mCustomViewContainer);
    }

    @Override
    public void onHideCustomView() {
        if (mCustomView == null) {
            return;
        } else {
            // Hide the custom view.  
            mCustomView.setVisibility(View.GONE);
            // Remove the custom view from its container.  
            mCustomViewContainer.removeView(mCustomView);
            mCustomView = null;
            mCustomViewContainer.setVisibility(View.GONE);
            mCustomViewCallback.onCustomViewHidden();
            // Show the content view.  
            mContentView.setVisibility(View.VISIBLE);
            setContentView(mContentView);
        }
    }

    
    @Override
    public void onBackPressed() {
        if (mCustomViewContainer != null)
            mWebChromeClient.onHideCustomView();
        else if (myWebView.canGoBack())
            myWebView.goBack();
        else
            super.onBackPressed();
    }
    */
}
