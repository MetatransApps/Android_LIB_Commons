package org.metatrans.commons.ui.list;


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ListAdapter_IdTD extends BaseAdapter {
	
	
    private Context context;
    private List<RowItem_IdTD> items;
    
	private int resID_listitem;
	private int resID_icon;
	private int resID_title;
	private int resID_description;
	
	
    public ListAdapter_IdTD(Context _context, List<RowItem_IdTD> _items,int _resID_listitem, int _resID_icon, int _resID_title, int _resID_description) {
        super();
        
    	resID_listitem = _resID_listitem;
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
    public boolean isEnabled(int position) {
		return items.get(position).isAvailable();
    }
	
    
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        LayoutInflater inflater = LayoutInflater.from(context);
        
        if (convertView == null) {
            convertView = inflater.inflate(resID_listitem, parent, false);
        }
        
        ImageView imageView = (ImageView) convertView.findViewById(resID_icon);
        TextView txtTitle = (TextView) convertView.findViewById(resID_title);
        TextView txtDesc = (TextView) convertView.findViewById(resID_description);
        
        //Fill with data
        RowItem_IdTD rowItem = (RowItem_IdTD) getItem(position);
        imageView.setImageDrawable(rowItem.getDrawable());
        txtDesc.setText(rowItem.getDesc());
        txtTitle.setText(rowItem.getTitle());
        
        return convertView;
    }
}
