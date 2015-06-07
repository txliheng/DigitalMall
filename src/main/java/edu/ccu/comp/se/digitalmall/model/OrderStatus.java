package edu.ccu.comp.se.digitalmall.model;


public enum OrderStatus {

	TRADE_NO_CREATE_PAY("没有创建支付宝交易"),
    WAIT_BUYER_PAY("等待买家付款"),
    WAIT_SELLER_SEND_GOODS("等待卖家发货"),//即:买家已付款
    WAIT_BUYER_CONFIRM_GOODS("等待买家确认收货"),//即:卖家已发货
    TRADE_BUYER_SIGNED("买家已签收"),//货到付款专用
    TRADE_FINISHED("交易成功"),
    TRADE_CLOSED("交易自动关闭"),//付款以后用户退款成功，交易自动关闭
    TRADE_CLOSED_BY_TAOBAO("主动关闭交易"),//付款以前，卖家或买家主动关闭交易
    PAY_PENDING("国际信用卡支付付款确认中");
    
    
	private final String info;
	private OrderStatus(String info){
		this.info = info;
	}
	public String getInfo() {
		return info;
	}	
}
