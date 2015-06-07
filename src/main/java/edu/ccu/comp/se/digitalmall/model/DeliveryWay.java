package edu.ccu.comp.se.digitalmall.model;

public enum DeliveryWay {
	free("卖家包邮"),
	postage("平邮") ,
	express("快递"),
	urgent_express("加急快递"),	
	ems("EMS");
	private final String info;
	private DeliveryWay(String info){
		this.info = info;
	}
	public String getInfo() {
		return info;
	}
}
