package org.metatrans.commons.ui.list;


import android.graphics.drawable.Drawable;


public class RowItem_IdTD {
    

	private boolean available;
    private boolean checked;
	private Drawable drawable;
    private String title;
    private String desc;
    
    
    public RowItem_IdTD(boolean available, Drawable drawable, String title, String desc) {
    	this(available, false, drawable, title, desc);
    }


    public RowItem_IdTD(boolean available, boolean checked, Drawable drawable, String title, String desc) {
        this.available = available;
        this.checked = checked;
        this.drawable = drawable;
        this.title = title;
        this.desc = desc;
    }


    public RowItem_IdTD(Drawable drawable, String title, String desc) {
        this(true, drawable, title, desc);
    }


    public boolean isChecked() {
        return checked;
    }


    public Drawable getDrawable() {
        return drawable;
    }
    
    
    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
    
    
    public String getDesc() {
        return desc;
    }
    
    
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    
    public String getTitle() {
        return title;
    }
    
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    
    @Override
    public String toString() {
        return title + "\n" + desc;
    }
    
    
	public boolean isAvailable() {
		return available;
	}
}
