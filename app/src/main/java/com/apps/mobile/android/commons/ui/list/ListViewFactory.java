package com.apps.mobile.android.commons.ui.list;


import java.util.List;

import com.apps.mobile.android.commons.R;
import com.apps.mobile.android.commons.ui.LayoutFactor_Base;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ListView;


public class ListViewFactory {
	
	
	public static ViewGroup create_ITD_ByXML(Activity activity, LayoutInflater inflater, List<RowItem_IdTD> rowItems, OnItemClickListener listener) {
		
		return create_ITD_ByXML(activity, inflater, rowItems, listener, AbsListView.CHOICE_MODE_NONE);
	}

	
	public static ViewGroup create_ITD_ByXML_NoChoice(Activity activity, LayoutInflater inflater, List<RowItem_IdTD> rowItems, OnItemClickListener listener) {
		return create_ITD_ByXML(activity, inflater, rowItems, listener, AbsListView.CHOICE_MODE_NONE);
	}
	
	
	private static ViewGroup create_ITD_ByXML(Activity activity, LayoutInflater inflater, List<RowItem_IdTD> rowItems, OnItemClickListener listener, int mode) {
		
		FrameLayout view = (FrameLayout) inflater.inflate(R.layout.commons_listview_layout, null);
		
		ListAdapter_IdTD adapter = new ListAdapter_IdTD(activity, rowItems,
				R.layout.commons_listview_item_itd,
				R.id.commons_listitem_icon,
				R.id.commons_listitem_title,
				R.id.commons_listitem_description);
		
		ListView list = (ListView) view.findViewById(R.id.commons_listview);
		list.setAdapter(adapter);
		list.setOnItemClickListener(listener);
		
		list.setChoiceMode(mode);
		
	    return view;
	}
	
	
	public static ViewGroup create_CITD_ByXML(Activity activity, LayoutInflater inflater, List<RowItem_CIdTD> rowItems, int initialSelection, OnItemClickListener listener) {
		
		FrameLayout view = (FrameLayout) inflater.inflate(R.layout.commons_listview_layout, null);
		
		ListAdapter_CIdTD adapter = new ListAdapter_CIdTD(activity, rowItems,
				R.layout.commons_listview_item_citd,
				R.id.commons_listitem_radio,
				R.id.commons_listitem_icon,
				R.id.commons_listitem_title,
				R.id.commons_listitem_description);
		
		ListView list = (ListView) view.findViewById(R.id.commons_listview);
		list.setAdapter(adapter);
		list.setOnItemClickListener(listener);
		
		list.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
		list.setSelection(initialSelection);
		list.setItemChecked(initialSelection, true);
		
	    return view;
	}
	
	
	public static ViewGroup create_ITD_ByCode(Activity activity, LayoutInflater inflater, List<RowItem_IdTD> rowItems, OnItemClickListener listener) {
		
		LayoutFactor_Base.Result result = LayoutFactor_Base.createListviewLayout(activity);
		
		ViewGroup view = result.view;
		
		ListAdapter_IdTD adapter = new ListAdapter_IdTD(activity, rowItems,
				R.layout.commons_listview_item_itd,
				R.id.commons_listitem_icon,
				R.id.commons_listitem_title,
				R.id.commons_listitem_description);
		
		ListView list = (ListView) view.findViewById(result.ids[1]);
		list.setAdapter(adapter);
		list.setOnItemClickListener(listener);
		
	    return view;
	}
}
