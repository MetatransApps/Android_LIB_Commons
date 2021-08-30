package org.metatrans.commons.ui.list;


import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;


public class ListAdapter_CIdTD extends BaseAdapter {
	
	
    private Context context;
    private List<RowItem_CIdTD> items;
    
	private int resID_listitem;
	private int resID_radio;
	private int resID_icon;
	private int resID_title;
	private int resID_description;
	
	private int textcolour_backup = -123;
	
	
    public ListAdapter_CIdTD(Context _context, List<RowItem_CIdTD> _items, int _resID_listitem, int _resID_radio, int _resID_icon, int _resID_title, int _resID_description) {
        
    	super();
        
    	resID_listitem = _resID_listitem;
        resID_radio = _resID_radio;
        resID_icon = _resID_icon;
        resID_title = _resID_title;
        resID_description = _resID_description;
        
        context = _context;
        items = _items;
    }
	
	
	@Override
    public int getCount() {
        return items.size();
    }
    
    
	@Override
    public Object getItem(int position) {
        return items.get(position);
    }
    
    
	@Override
    public long getItemId(int position) {
        return position;
    }
	
	
	@Override
    public boolean areAllItemsEnabled() {
        return false;
    }
	
	
	@Override
    public boolean isEnabled(int position) {
		return items.get(position).isAvailable();
    }
	
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
		
        LayoutInflater inflater = LayoutInflater.from(context);
        
        if (convertView == null) {
            convertView = inflater.inflate(resID_listitem, parent, false);
        }
        
        RadioButton radiobutton = (RadioButton) convertView.findViewById(resID_radio);
        ImageView imageView = (ImageView) convertView.findViewById(resID_icon);
        TextView txtTitle = (TextView) convertView.findViewById(resID_title);
        TextView txtDesc = (TextView) convertView.findViewById(resID_description);
        
        if (textcolour_backup == -123) {
        	textcolour_backup = txtDesc.getCurrentTextColor();
        }
        
        //Fill with data
        RowItem_CIdTD rowItem = (RowItem_CIdTD) getItem(position);
        radiobutton.setChecked(rowItem.isChecked());
        imageView.setImageDrawable(rowItem.getDrawable());
        txtTitle.setText(rowItem.getTitle());
        txtDesc.setText(rowItem.getDesc());
        
        if (!rowItem.isAvailable() && !rowItem.isChecked()) {
        	txtDesc.setTextColor(Color.rgb(Color.red(textcolour_backup), Math.max(0, Color.green(textcolour_backup) - 50), Math.max(0, Color.blue(textcolour_backup)) - 50));
        } else {
        	txtDesc.setTextColor(textcolour_backup);
        }
        
        radiobutton.setClickable(false);
        radiobutton.setFocusable(false);
        radiobutton.setFocusableInTouchMode(false);
        
        if (rowItem.isChecked()) {
            radiobutton.setChecked(true);
        } else {
            radiobutton.setChecked(false);
        }
        
        return convertView;
    }
}
