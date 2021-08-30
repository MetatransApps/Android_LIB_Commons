package org.metatransapps.commons.cfg;


public interface IConfigurationEntry {
	
	public int getID();
	public int getName();
	public int getIconResID();
	public int getDescription();
	
	public String getName_String();
	public String getDescription_String();
}
