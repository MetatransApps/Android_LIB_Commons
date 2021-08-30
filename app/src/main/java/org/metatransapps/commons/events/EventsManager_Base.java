package org.metatransapps.commons.events;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.metatransapps.commons.DeviceUtils;
import org.metatransapps.commons.analytics.IAnalytics;
import org.metatransapps.commons.app.Application_Base;
import org.metatransapps.commons.events.api.IEvent_Base;
import org.metatransapps.commons.events.api.IEventsManager;
import org.metatransapps.commons.model.GameData_Base;
import org.metatransapps.commons.model.UserSettings_Base;
import org.metatransapps.commons.storage.StorageUtils;

import android.app.Activity;
import android.content.Context;


public class EventsManager_Base implements IEventsManager {
	
	
	private static final String FILE_NAME = "events";
	private static final Object sync_events = new Object();
	
	protected final ExecutorService executor;
	private final IAnalytics analytics;
	
	
	public EventsManager_Base(ExecutorService _executor, IAnalytics _analytics) {
		executor = _executor;
		analytics = _analytics;
	}
	
	
	@Override
	public long getLastGameChange(Context context) {
		
		long result = 0;
		
		try {
			synchronized (sync_events) {
				EventsData_Base eventsData_Base = (EventsData_Base) StorageUtils.readStorage(context, FILE_NAME);
				result = eventsData_Base.last_game_change;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	@Override
	public EventsData_Base getEventsData(Context context) {
		synchronized (sync_events) {
			Object obj = StorageUtils.readStorage(context, FILE_NAME);
			if (obj instanceof EventsData_Base) {
				//System.out.println("EventsManager_Base: getEventsData: return obj=" + obj);
				return (EventsData_Base) obj;
			} else {
				System.out.println("EventsManager_Base: getEventsData: return null, because NOT instance of EventsData_Base");
				return null;
			}
		}
	}
	
	
	@Override
	public long getLastMainScreenInteraction(Context context) {
		
		long result = 0;
		
		try {
			synchronized (sync_events) {
				EventsData_Base eventsData_Base = (EventsData_Base) StorageUtils.readStorage(context, FILE_NAME);
				result = eventsData_Base.last_mainscreen_interaction;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	@Override
	public IEvent_Base create(int id, String name) {
		return create(id, id, name, name);
	}
	
	
	@Override
	public IEvent_Base create(int id, int subid, String name, String subname) {
		return create(id, subid, name, subname, 0);
	}
	

	@Override
	public IEvent_Base create(int id, int subid, String name, String subname, long value) {
		return create(id, subid, subid, name, subname, subname, value);
	}
	

	@Override
	public IEvent_Base create(int id, int subid, int subsubid, String name, String subname, String subsubname) {
		return create(id, subid, subsubid, name, subname, subsubname, 0);
	}
	
	
	@Override
	public IEvent_Base create(int id, int subid, int subsubid, String name, String subname, String subsubname, long value) {
		return new Event_Base(id, subid, subsubid, name, subname, subsubname, value);
	}
	

	@Override
	public void register(Context context, List<IEvent_Base> events) {
		
		System.out.println("NEW EVENTS: " + events);
		
		synchronized (sync_events) {
			try {
				EventsData_Base eventsData_Base = (EventsData_Base) StorageUtils.readStorage(context, FILE_NAME);
				if (eventsData_Base == null) {
					eventsData_Base = new EventsData_Base();
					StorageUtils.writeStore(context, FILE_NAME, eventsData_Base);
					eventsData_Base = (EventsData_Base) StorageUtils.readStorage(context, FILE_NAME);
				}
				
				if (eventsData_Base.events_local == null) {
					eventsData_Base.events_local = new ArrayList<IEvent_Base>();
				}
				
				eventsData_Base.events_local.addAll(events);
				
				StorageUtils.writeStore(context, FILE_NAME);
				
				sync_events.notifyAll();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@Override
	public void register(Context context, final IEvent_Base event) {
		
		System.out.println("NEW EVENT: " + event);
		
		synchronized (sync_events) {			
			try {
				EventsData_Base eventsData_Base = (EventsData_Base) StorageUtils.readStorage(context, FILE_NAME);
				if (eventsData_Base == null) {
					eventsData_Base = new EventsData_Base();
					StorageUtils.writeStore(context, FILE_NAME, eventsData_Base);
					eventsData_Base = (EventsData_Base) StorageUtils.readStorage(context, FILE_NAME);
				}
				
				if (eventsData_Base.events_local == null) {
					eventsData_Base.events_local = new ArrayList<IEvent_Base>();
				}
				
				eventsData_Base.events_local.add(event);
				
				StorageUtils.writeStore(context, FILE_NAME);
				
				sync_events.notifyAll();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@Override
	public void handleGameEvents_OnStart(Activity activity, GameData_Base data) {
		
		if (data.isCountedAsStarted()) {
			System.out.println("EventsManager_Base/handleGameEvents_OnStart: " + " game is already counted");
			return;			
		}
		
		data.setCountedAsStarted();
		
		register(activity, create(IEvent_Base.START_GAME, "START_GAME"));
	}
	
	
	@Override
	public void handleGameEvents_OnExit(Activity activity, GameData_Base data, UserSettings_Base settings) {
		
		if (data.isCountedAsExited()) {
			System.out.println("EventsManager_Base/handleGameEvents_OnExit: " + " game is already counted");
			return;			
		}
		
		System.out.println("EventsManager_Base/handleGameEvents_OnExit: " + " game is NOT changed and will be counted");
		data.setCountedAsExited();
		
		
		long timeInMainScreen_inSec = data.getAccumulated_time_inmainscreen() / 1000;
		
		int modeID = settings.modeID;
		int coloursID = settings.uiColoursID;
		
		boolean online = DeviceUtils.isConnectedOrConnecting();
		
		//GameStatistics.addPlayTime(activity, boardmanagerID, difficultyID, data.getAccumulated_time_inmainscreen() / 1000);
		
		List<IEvent_Base> events = new ArrayList<IEvent_Base>();
		
				
		events.add(create(
				IEvent_Base.EXIT_GAME, IEvent_Base.EXIT_GAME_TOTAL,
				"EXIT_GAME", "TOTAL",
				timeInMainScreen_inSec));
		
		events.add(create(
				IEvent_Base.EXIT_GAME, IEvent_Base.EXIT_GAME_MODE, modeID,
				"EXIT_GAME", "DIFFICULTY", "" + modeID,
				timeInMainScreen_inSec));
		
		events.add(create(
				IEvent_Base.EXIT_GAME, IEvent_Base.EXIT_GAME_COLOURS, coloursID,
				"EXIT_GAME", "COLOURS", "" + coloursID,
				timeInMainScreen_inSec));
		
		events.add(create(
				IEvent_Base.EXIT_GAME, IEvent_Base.EXIT_GAME_ONLINE, online ? 1 : 0,
				"EXIT_GAME", "ONLINE", "" + online,
				timeInMainScreen_inSec));

		if (Application_Base.getInstance() != null) {
			
			events.add(create(
					IEvent_Base.EXIT_GAME, IEvent_Base.EXIT_GAME_STORE, Application_Base.getInstance().getAppStore().getID(),
					"EXIT_GAME", "STORE", Application_Base.getInstance().getAppStore().getName(),
					timeInMainScreen_inSec));
		}
		
		register(activity, events);
	}


	@Override
	public void handleGameEvents_OnFinish(Activity activity, GameData_Base data, UserSettings_Base settings, int gameStatus) {
		
		
		handleGameEvents_OnExit(activity, data, settings);
		
		
		if (data.isCountedAsCompleted()) {
			System.out.println("EventsManager_Base/handleGameEvents_OnFinish: " + " game is already counted");
			return;			
		}
		
		System.out.println("EventsManager_Base/handleGameEvents_OnFinish: " + " game is NOT changed and will be counted");
		data.setCountedAsCompleted();
		
		/**
		 * Anti-cheat conditions
		 */
		if (getLastGameChange(activity) > data.getCreatedAt() + 30 * 1000) {
			System.out.println("EventsManager_Base/handleGameEvents_OnFinish: " + " game is changed and will NOT be counted");
			return;
		}
		
		//long timeInMainScreen_inSec = data.getAccumulated_time_inmainscreen() / 1000;
		
		
		//TODO: Handle wins
	}
	
	
	@Override
	public void init(final Application_Base app_context) {
		
		if (app_context == null) {
			return;
		}
		
		Object eventsData_Base = StorageUtils.readStorage(app_context, FILE_NAME);
		if (eventsData_Base == null || !(eventsData_Base instanceof EventsData_Base)) {
			eventsData_Base = new EventsData_Base();
			StorageUtils.writeStore(app_context, FILE_NAME, eventsData_Base);
			eventsData_Base = StorageUtils.readStorage(app_context, FILE_NAME);
		}
		
		
		//Local events processor
		executor.submit(new Runnable() {
			
			@Override
			public void run() {
				
				while (true) {
					
					try {
						
						synchronized (sync_events) {
							
							EventsData_Base eventsData_Base = (EventsData_Base) StorageUtils.readStorage(app_context, FILE_NAME);
							
							if (eventsData_Base != null) {
								
								if (eventsData_Base.events_local != null && eventsData_Base.events_local.size() > 0) {
									
									IEvent_Base first_event = eventsData_Base.events_local.get(0);
									
									System.out.println("PROCESS LOCAL EVENT: " + first_event);
									
									try {
										handleEventsLocal(app_context, first_event);
										handleAchievements(app_context, first_event);
										
										eventsData_Base.events_local.remove(0);
										
										if (eventsData_Base.events_remote == null) {
											eventsData_Base.events_remote = new ArrayList<IEvent_Base>();
										}
										eventsData_Base.events_remote.add(first_event);
										
										StorageUtils.writeStore(app_context, FILE_NAME);
										
										System.out.println("PROCESS LOCAL EVENT: OK");
										
									} catch(Exception e) {
										
										System.out.println("PROCESS LOCAL EVENT: FAILED");
										e.printStackTrace();
									}
									
								} else {
									sync_events.wait();//1000
								}
							} else {
								sync_events.wait();//1000
							}
						}
						
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		});	
		
		
		//Remote events processor
		executor.submit(new Runnable() {
			
			@Override
			public void run() {
				
				while (true) {
					
					try {
						
						synchronized (sync_events) {
							
							EventsData_Base eventsData_Base = (EventsData_Base) StorageUtils.readStorage(app_context, FILE_NAME);
							
							if (eventsData_Base != null) {
								
								if (DeviceUtils.isConnectedOrConnecting()) {
									
									if (eventsData_Base.events_remote != null && eventsData_Base.events_remote.size() > 0) {
										
										IEvent_Base first_event = eventsData_Base.events_remote.get(0);
										
										System.out.println("PROCESS REMOTE EVENT: " + first_event);
										
										try {
											
											handleEventsRemote(first_event);
											
											eventsData_Base.events_remote.remove(0);
											
											StorageUtils.writeStore(app_context, FILE_NAME);
											
											System.out.println("PROCESS REMOTE EVENT: OK");
											
										} catch(Exception e) {
											
											System.out.println("PROCESS REMOTE EVENT: FAILED");
											e.printStackTrace();
										}
									
									} else {
										sync_events.notifyAll();
										sync_events.wait();
									}
								} else {
									System.out.println("PROCESS REMOTE EVENT: POSTPONED (no connection)");
									sync_events.wait();//10000
								}
							} else {
								sync_events.wait();
							}
						}
						
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		});		

	}
	
	
	@Override
	public void updateLastMainScreenInteraction(Context context, long timestamp) {
		
		synchronized (sync_events) {
			EventsData_Base eventsData_Base = (EventsData_Base) StorageUtils.readStorage(context, FILE_NAME);
			
			if (eventsData_Base == null) {
				eventsData_Base = new EventsData_Base();
				StorageUtils.writeStore(context, FILE_NAME, eventsData_Base);
				eventsData_Base = (EventsData_Base) StorageUtils.readStorage(context, FILE_NAME);
			}
			
			eventsData_Base.last_mainscreen_interaction = timestamp;
			StorageUtils.writeStore(context, FILE_NAME);
		}
	}
	
	
	protected boolean isGameChange(final IEvent_Base event) {
		return event.getID() == IEvent_Base.MENU_OPERATION && event.getSubID() == IEvent_Base.MENU_OPERATION_CHANGE_MODE;
	}
	
	
	private void handleEventsLocal(Context context, final IEvent_Base event) {
		
		//Check for game validity from "difficulty" and "auto" point of view
		if (isGameChange(event)) {
		
			synchronized (sync_events) {
				//Store timestamp of last change
				EventsData_Base eventsData_Base = (EventsData_Base) StorageUtils.readStorage(context, FILE_NAME);
				if (eventsData_Base == null) {
					eventsData_Base = new EventsData_Base();
					StorageUtils.writeStore(context, FILE_NAME, eventsData_Base);
					eventsData_Base = (EventsData_Base) StorageUtils.readStorage(context, FILE_NAME);
				}
				
				eventsData_Base.last_game_change = System.currentTimeMillis();
				StorageUtils.writeStore(context, FILE_NAME);
			}
		}
	}
	
	
	protected boolean mustProcessRemote(final IEvent_Base event) {
		
		//Skip some events, which are appearing very often
		//or are making the analytics data wrong
		if (event.getID() == IEvent_Base.NOTIFICATIONS) {
			
			if (event.getSubID() == IEvent_Base.NOTIFICATIONS_TIMEOFF_REMINDER_FIRED) {
				
				return false;
			}
			
			if (event.getSubID() == IEvent_Base.NOTIFICATIONS_TIMEOFF_REMINDER_MISSED) {
				
				return false;
			}
			
		} else if (event.getID() == IEvent_Base.CONNECTIVENESS) {
			
			return false;
		}
		
		return true;
	}
	
	
	private void handleEventsRemote(final IEvent_Base event) {
		
		if (mustProcessRemote(event)) {
		
			analytics.sendEvent(event);
		
		}
	}
	
	
	protected void handleAchievements(Context context, IEvent_Base event) {
		//Do nothing
	}
}
