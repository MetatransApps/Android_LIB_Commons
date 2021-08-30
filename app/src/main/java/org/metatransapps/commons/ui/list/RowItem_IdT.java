package org.metatransapps.commons.ui.list;


import android.graphics.drawable.Drawable;


public class RowItem_IdT {
    
	
	private Drawable drawable;
	private String title;    
    
	
    public RowItem_IdT(Drawable drawable, String title) {
        this.drawable = drawable;
        this.title = title;
        this.title = title;
    }
    
    
	public Drawable getDrawable() {
		return drawable;
	}


	public void setDrawable(Drawable drawable) {
		this.drawable = drawable;
	}

	
	public String getTitle() {
		return title;
	}

	
	public void setTitle(String title) {
		this.title = title;
	}
	
    
    @Override
    public String toString() {
        return title;
    }
}
