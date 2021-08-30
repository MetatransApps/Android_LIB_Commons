package org.metatransapps.commons.cfg;


import org.metatransapps.commons.app.Application_Base;


public abstract class ConfigurationEntry_Base implements IConfigurationEntry {

	
	@Override
	public int getDescription() {
		return 0;
	}
	
	
	
	@Override
	public String getName_String() {
		return Application_Base.getInstance().getString(getName());
	}


	@Override
	public String getDescription_String() {
		return getDescription() == 0 ? "" : Application_Base.getInstance().getString(getDescription());
	}
}
