package org.metatrans.commons.events.api;


import java.util.List;

import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.events.EventsData_Base;
import org.metatrans.commons.model.GameData_Base;
import org.metatrans.commons.model.UserSettings_Base;

import android.app.Activity;
import android.content.Context;


public interface IEventsManager {

	public void init(final Application_Base app_context);
	
	public EventsData_Base getEventsData(Context context);
	
	public long getLastGameChange(Context context);
	public long getLastMainScreenInteraction(Context context);
	public void updateLastMainScreenInteraction(Context context, long timestamp);
	
	/*public IEvent_Base create(int id, String name);
	public IEvent_Base create(int id, int subid, String name, String subname);
	public IEvent_Base create(int id, int subid, String name, String subname, long value);
	public IEvent_Base create(int id, int subid, int subsubid, String name, String subname, String subsubname);
	public IEvent_Base create(int id, int subid, int subsubid, String name, String subname, String subsubname, long value);
	 */

	public void register(Context context, List<IEvent_Base> events);
	public void register(Context context, final IEvent_Base event);
	
	public void handleGameEvents_OnStart(Activity activity, GameData_Base data);
	public void handleGameEvents_OnExit(Activity activity, GameData_Base data, UserSettings_Base settings);
	public void handleGameEvents_OnFinish(Activity activity, GameData_Base data, UserSettings_Base settings, int gameStatus);
}
