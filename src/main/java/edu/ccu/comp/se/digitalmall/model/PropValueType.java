package edu.ccu.comp.se.digitalmall.model;


/**
 * 属性值类型
 * <p> @author Jerome
 * <p> @date 2015.5.17
 * <p> @version 1.0
 */
public enum PropValueType {
	MultiCheck("枚举多选"),
	Optional("枚举单选"),
	MultiCheckText("枚举可输入多选"),
	OptionalText("枚举可输入单选"),
	Text("非枚举可输入");
	private final String info;
	private PropValueType(String info){
		this.info = info;
	}
	public String getInfo() {
		return info;
	}
	
}
