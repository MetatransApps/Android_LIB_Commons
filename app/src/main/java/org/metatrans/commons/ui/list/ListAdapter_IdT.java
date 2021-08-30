package org.metatrans.commons.ui.list;


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ListAdapter_IdT extends BaseAdapter {
	
	
    private Context context;
    private List<RowItem_IdT> items;
    
	private int resID_listitem;
	private int resID_icon;
	private int resID_title;
	
	
    public ListAdapter_IdT(Context _context, List<RowItem_IdT> _items, int _resID_listitem, int _resID_icon, int _resID_title) {
        
    	super();
        
    	resID_listitem = _resID_listitem;
        resID_icon = _resID_icon;
        resID_title = _resID_title;
        
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
    public View getView(int position, View convertView, ViewGroup parent) {
		
        LayoutInflater inflater = LayoutInflater.from(context);
        
        if (convertView == null) {
            convertView = inflater.inflate(resID_listitem, parent, false);
        }
        
        ImageView imageView = (ImageView) convertView.findViewById(resID_icon);
        TextView txtTitle = (TextView) convertView.findViewById(resID_title);
        
        //Fill with data
        RowItem_IdT rowItem = (RowItem_IdT) getItem(position);
        imageView.setImageDrawable(rowItem.getDrawable());
        txtTitle.setText(rowItem.getTitle());
                
        return convertView;
    }
}
