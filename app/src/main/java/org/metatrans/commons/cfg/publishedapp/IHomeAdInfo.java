package org.metatrans.commons.cfg.publishedapp;


import org.metatrans.commons.cfg.ItemInfo;


public interface IHomeAdInfo extends ItemInfo {

    public boolean isPaid();
    public boolean hasAds();
    public String getMarketURL();

}
