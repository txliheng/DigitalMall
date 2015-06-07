package edu.ccu.comp.se.digitalmall.model;

public enum ItemStatus {

	normal("正常状态"),delete("删除状态");
	private final String info;
	private ItemStatus(String info){
		this.info = info;
	}
	public String getInfo() {
		return info;
	}
	
	
}
