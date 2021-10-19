package org.metatrans.commons.cfg.menu;


import org.metatrans.commons.R;
import org.metatrans.commons.app.Application_Base;


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
		return R.drawable.ic_action_users_white;
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
