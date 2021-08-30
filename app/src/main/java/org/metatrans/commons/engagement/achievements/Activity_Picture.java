package org.metatrans.commons.engagement.achievements;


import org.metatrans.commons.Activity_Base;
import org.metatrans.commons.R;
import org.metatrans.commons.ui.utils.BitmapUtils;
import org.metatrans.commons.ui.utils.ScreenUtils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class Activity_Picture extends Activity_Base {
	
	
	private int last_image_id = -1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		System.out.println("Activity_Picture:  onCreate");
		
		super.onCreate(savedInstanceState);
		
		initUI();
	}


	
	@Override
	public void onResume() {
		
		System.out.println("Activity_Picture:  onResume");
		
		super.onResume();
		
		initUI();
	}
	
	
	@Override
	protected int getBackgroundImageID() {
		//boolean left_handed = (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
		//return left_handed ? R.drawable.ic_scores : R.drawable.ic_scores;
		return R.drawable.ic_hearts;
	}
	
	
	private void initUI() {
		
		Bundle b = getIntent().getExtras();
		int imageID = b.getInt("imageID");
		
		System.out.println("Activity_Commons_Catalog_Picture: initUI: imageID=" + imageID + ", last_image_id=" + last_image_id);
		
		if (imageID == last_image_id) {
			return;
		}
		last_image_id = imageID;
		
		setContentView(R.layout.catalog_picture);
		
		String imageTitle = b.getString("imageTitle");
		
		//Bitmap bitmap = getBitmapCache().getBitmap(this, imageID);
		Bitmap bitmap_org = BitmapUtils.fromResource(this, imageID);
		int[] screen_size = ScreenUtils.getScreenSize(this);
		Bitmap bitmap = BitmapUtils.fitInRect(bitmap_org, screen_size[0], screen_size[1]);
		
		Drawable drawable = BitmapUtils.createDrawable(this, bitmap);
		drawable.setAlpha(255);
		
		ImageView imageView = (ImageView) findViewById(R.id.catalog_picture_image);
		imageView.setImageDrawable(drawable);
		
		if (imageTitle == null) {
			imageTitle = "";
		}
		System.out.println("PICTURE TITLE: " + imageTitle);
		TextView textView = (TextView) findViewById(R.id.catalog_picture_title);
		textView.setText(imageTitle);
		
		
		setBackgroundPoster(R.id.catalog_picture_layout);
	}
}
