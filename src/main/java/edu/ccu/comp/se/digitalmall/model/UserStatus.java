package edu.ccu.comp.se.digitalmall.model;

public enum UserStatus {
	normal("正常"),inactive("未激活"),delete("删除"),reeze("冻结"),supervise("监管");
	private final String info;
	private UserStatus(String info){
		this.info = info;
	}
	public String getInfo() {
		return info;
	}
}
