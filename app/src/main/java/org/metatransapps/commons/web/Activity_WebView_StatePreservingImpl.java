package org.metatransapps.commons.web;


import org.metatransapps.commons.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.MailTo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.PluginState;
import android.widget.FrameLayout;
import android.widget.Toast;


//Intent intent = new Intent(this, StandardImplActivity.class);
//startActivity(intent);
public class Activity_WebView_StatePreservingImpl extends Activity {
	
	
	protected FrameLayout frame;
	protected WebView webView;
	
	private String URL = null;
	private int titleID = -1;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		System.out.println("Activity_WebView_StatePreservingImpl: onCreate savedInstanceState=" + savedInstanceState);
		
		super.onCreate(savedInstanceState);
		
		if (savedInstanceState != null) {
			
			URL = savedInstanceState.getString("URL");
			titleID = savedInstanceState.getInt("titleID");
						
		} else {
			
			URL = getIntent().getExtras().getString("URL");
			titleID = getIntent().getExtras().getInt("titleID");
		}
		
		// Initialize the UI
		initUI();
		
		if (savedInstanceState != null) {
			getWebView().restoreState(savedInstanceState);
		}
	}
	
	
	private void initUI() {
		
		// Initialize the WebView if necessary
		if (getWebView() == null) {
			
			// Load the layout resource for the new configuration
			setContentView(R.layout.webview);
			
			//setTitle(titleID);
			
			// Retrieve UI elements
			frame = ((FrameLayout)findViewById(R.id.layout_webview_frame));
			
			// Create the webview
			webView = createWebView();
			getWebView().setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
			
			/*webView.getSettings().setSupportZoom(true);
			webView.getSettings().setBuiltInZoomControls(true);
			webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
			webView.setScrollbarFadingEnabled(true);
			webView.getSettings().setLoadsImagesAutomatically(true);

			// Load the URLs inside the WebView, not in the external web browser
			webView.setWebViewClient(new WebViewClient());
			 */
			
			
			/*webView.getSettings().setJavaScriptEnabled(true);
			//webView.getSettings().setPluginsEnabled(true);
			if (Build.VERSION.SDK_INT < 8) {
				webView.getSettings().setPluginsEnabled(true);
		    } else {
		    	webView.getSettings().setPluginState(PluginState.ON);
		    }
			webView.getSettings().setSupportZoom(true);
			webView.getSettings().setBuiltInZoomControls(false);
			//webView.getSettings().setDisplayZoomControls(false); API >= 11
			webView.setHorizontalScrollBarEnabled(false);
			webView.setVerticalScrollBarEnabled(true);*/
			
			
			getWebView().getSettings().setSupportZoom(true);
			getWebView().getSettings().setBuiltInZoomControls(true);
			getWebView().setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
			getWebView().setScrollbarFadingEnabled(true);
			getWebView().getSettings().setJavaScriptEnabled(true);
			if (Build.VERSION.SDK_INT < 8) {
				//getWebView().getSettings().setPluginsEnabled(true);
		    } else {
		    	getWebView().getSettings().setPluginState(PluginState.ON);
		    }
			getWebView().getSettings().setLoadsImagesAutomatically(true);
            
            // Load the URLs inside the WebView, not in the external web browser
			getWebView().setWebViewClient(new SetWebClient());
            
			getWebView().setOnKeyListener(new OnKeyListener()
            {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event)
                {
                    if(event.getAction() == KeyEvent.ACTION_DOWN)
                    {
                        WebView webView = (WebView) v;

                        switch(keyCode)
                        {
                            case KeyEvent.KEYCODE_BACK:
                            	
                            	if (handleCustomView()) {
                            		return true;
                            	}
                            	
                            	if (URL == null) {
                            		finish();
                            		return true;
                            	}
                            	
                        		String cur_url = webView.getUrl();
                        		
                        		if (cur_url == null) {
                        			cur_url = "page_not_loaded_yet";
                        		}
                        		
                        		System.out.println("Activity_WebView_StatePreservingImpl: OnKeyListener webView != null");
                        		System.out.println("Activity_WebView_StatePreservingImpl: OnKeyListener cur_url=" + cur_url);
                        		
                        		webView.stopLoading();
                            	
                        		System.out.println("Activity_WebView_StatePreservingImpl: OnKeyListener webView.canGoBack() = " + webView.canGoBack());
                        		System.out.println("Activity_WebView_StatePreservingImpl: OnKeyListener Uri.parse(cur_url).equals(Uri.parse(URL)) = " + Uri.parse(cur_url).equals(Uri.parse(URL)));
                        		
                        		if (Uri.parse(cur_url).equals(Uri.parse(URL))) {
                        			finish();
                        			return true;
                        		}
                        		
                            	if (webView.canGoBack() && !Uri.parse(cur_url).equals(Uri.parse(URL))) {
                            		webView.goBack();
                            	} else {
                        			finish();
                            	}
                            	
                    			return true;
                        }
                    }

                    return false;
                }
            });
            
			// Load a page
			getWebView().loadUrl(URL);
			
			// Attach the WebView to its placeholder
			frame.addView(getViewToAttach());
		}
	}


	protected WebView createWebView() {
		WebView result = new WebView(this);
		result.setWebChromeClient(new WebChromeClient());
		return result;
	}
	
    
	protected View getViewToAttach() {
		return getWebView();
	}

	
	protected WebView getWebView() {
		return webView;
	}
	
	
	protected boolean handleCustomView() {
		return false;
	}
	
	
	private class SetWebClient extends WebViewClient {
		
		
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
        	
            if (url.startsWith("mailto:")) {
                
            	MailTo mt = MailTo.parse(url);
                Intent i = newEmailIntent(Activity_WebView_StatePreservingImpl.this, mt.getTo(), mt.getSubject(), mt.getBody(), mt.getCc());
                startActivity(i);
                view.reload();
                
                return true;
                
            } else {
            	
                view.loadUrl(url);
            }
            	
            return true;
        }
		
        
        public Intent newEmailIntent(Context context, String address, String subject, String body, String cc) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] { address });
            intent.putExtra(Intent.EXTRA_TEXT, body);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_CC, cc);
            intent.setType("message/rfc822");
            return intent;
      }
        
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        	
            /*if (view.canGoBack()) {
                view.goBack();
            }*/
            
            Toast toast = Toast.makeText(getBaseContext(), description, Toast.LENGTH_SHORT);
            
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
            
            toast.show();
        }
    }
    
    
	@Override
    public void onStop() {
        
		super.onStop();
        
        if (getWebView() != null) {
        	getWebView().stopLoading();
        }
    }
    
    
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		
		super.onConfigurationChanged(newConfig);
		
		System.out.println("Activity_WebView_StatePreservingImpl: onConfigurationChanged");
		
		/*if (getWebView() != null) {
			// Remove the WebView from the old placeholder
			//frame.removeView(getViewToAttach());
			
			//WebView view = getWebView();
			//webView = null;
			//frame = null;
		}*/
		
		// Reinitialize the UI
		initUI();
	}
	
	
	/*@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
    	System.out.println("Activity_WebView_StatePreservingImpl: onKeyDown");
    	
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	    	System.out.println("Activity_WebView_StatePreservingImpl: onKeyDown: BACK");
	        return true;
	    }
	    
	    return super.onKeyDown(keyCode, event);
	}*/
	
	
    @Override
    public void onBackPressed() {
    	
    	System.out.println("Activity_WebView_StatePreservingImpl: onBackPressed");
    	
    	if (getWebView() != null) {
    		
        	if (handleCustomView()) {
        		return;
        	}
    		
    		String cur_url = getWebView().getUrl();
    		
    		if (cur_url == null) {
    			cur_url = "page_not_loaded_yet";
    		}
    		
    		System.out.println("Activity_WebView_StatePreservingImpl: onBackPressed webView != null");
    		System.out.println("Activity_WebView_StatePreservingImpl: onBackPressed cur_url=" + cur_url);
    		
    		getWebView().stopLoading();
        	
    		System.out.println("Activity_WebView_StatePreservingImpl: onBackPressed webView.canGoBack() = " + getWebView().canGoBack());
    		System.out.println("Activity_WebView_StatePreservingImpl: onBackPressed Uri.parse(cur_url).equals(Uri.parse(URL)) = " + Uri.parse(cur_url).equals(Uri.parse(URL)));
    		
    		if (Uri.parse(cur_url).equals(Uri.parse(URL))) {
    			super.onBackPressed();
    			return;
    		}
    		
        	if (getWebView().canGoBack() && !Uri.parse(cur_url).equals(Uri.parse(URL))) {
        		
        		getWebView().goBack();
        		
        		return;
        	}
    	}
    	
    	System.out.println("Activity_WebView_StatePreservingImpl: onBackPressed DEFAULT");
    	
        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }
    
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		
		System.out.println("Activity_WebView_StatePreservingImpl: onSaveInstanceState");
		
		super.onSaveInstanceState(outState);

		outState.putString("URL", URL);
		outState.putInt("titleID", titleID);
		
		// Save the state of the WebView
		getWebView().saveState(outState);
	}
	
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		
		System.out.println("Activity_WebView_StatePreservingImpl: onRestoreInstanceState");
		
		super.onRestoreInstanceState(savedInstanceState);
		
		URL = savedInstanceState.getString("URL");
		titleID = savedInstanceState.getInt("titleID");
		
		// Restore the state of the WebView
		getWebView().restoreState(savedInstanceState);
	}
	
	
	@Override
	protected void onResume() {
	    super.onResume();
	    try {
	        //WebView.class.getMethod("onResume").invoke(getWebView());
	    	getWebView().onResume();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
	@Override
	protected void onPause() {
	    super.onPause();
	    try {
	        //WebView.class.getMethod("onPause").invoke(getWebView());
	        getWebView().onPause();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
