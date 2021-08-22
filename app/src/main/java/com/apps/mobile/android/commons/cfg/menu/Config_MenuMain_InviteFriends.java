package com.apps.mobile.android.commons.cfg.menu;


import com.apps.mobile.android.commons.R;
import com.apps.mobile.android.commons.app.Application_Base;


public class Config_MenuMain_InviteFriends extends Config_MenuMain_Base {
	
	
	@Override
	public int getID() {
		return CFG_MENU_INVITE_FRIENDS;
	}
	
	
	@Override
	public int getName() {
		return R.string.label_invite;
	}
	
	
	@Override
	public int getIconResID() {
		return R.drawable.ic_invite_white;
	}
	
	
	@Override
	public String getDescription_String() {
		return "";
	}
	
	
	@Override
	public Runnable getAction() {
		
		return new Runnable() {
			
			@Override
			public void run() {
				
				//Invite
				Application_Base.getInstance().getEngagementProvider().getSocialProvider().openInviteDialog();
			}
		};
	}
}
