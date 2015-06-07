package edu.ccu.comp.se.digitalmall.service;

import edu.ccu.comp.se.digitalmall.model.ItemSku;

public interface IItemSkuService {

	public ItemSku find(Long itemId,String props);
	public ItemSku find(Long itemSkuId);
}
