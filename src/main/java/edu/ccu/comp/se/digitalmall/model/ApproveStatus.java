package edu.ccu.comp.se.digitalmall.model;

public enum ApproveStatus {
	onsale("出售中"),instock("库中");
	private final String info;
	private ApproveStatus(String info){
		this.info = info;
	}
	public String getInfo() {
		return info;
	}
}
