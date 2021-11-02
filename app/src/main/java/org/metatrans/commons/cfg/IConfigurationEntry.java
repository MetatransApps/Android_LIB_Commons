package org.metatrans.commons.cfg;


public interface IConfigurationEntry {
	
	public int getID();

	public int getIconResID();

	public int getName();
	public int getDescription();
	
	public String getName_String();
	public String getDescription_String();
}
