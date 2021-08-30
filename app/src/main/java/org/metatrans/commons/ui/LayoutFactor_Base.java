package org.metatrans.commons.ui;


import org.metatrans.commons.R;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListView;


public class LayoutFactor_Base {
	
	
	protected static volatile int CUR_ID = 123456789;
	
	
	public static class Result {
		public ViewGroup view;
		public int[] ids;
	}
	
	
	public static Result createListviewLayout(Context context) {
		
		Result result = new Result();
		
		result.ids = new int[] {CUR_ID++, CUR_ID++};
		
		result.view = createListviewLayout(context, result.ids[0], result.ids[1]);
		
		return result;
	}
	
	
	private static LinearLayout createListviewLayout(Context context, int layoutID, int listviewID) {
		
		
		//Root linear layout
		LinearLayout root = new LinearLayout(context);
		root.setId(layoutID);
		root.setOrientation(LinearLayout.VERTICAL);
		
		LinearLayout.LayoutParams root_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
		root.setLayoutParams(root_params);
		
		
		//List view view
		ListView listview = new ListView(context);
		listview.setId(listviewID);
		
		LinearLayout.LayoutParams listview_params = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		
		listview.setSelector(R.drawable.ic_blue3);
		listview.setDividerHeight(1);
		listview.setHeaderDividersEnabled(true);
		listview.setFooterDividersEnabled(true);
		
		listview.setLayoutParams(listview_params);
		
		
		//Adding list view to the linear layout
		root.addView(listview);
		
		
		return root;
	}
}
