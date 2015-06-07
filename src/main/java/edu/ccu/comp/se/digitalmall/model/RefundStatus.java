package edu.ccu.comp.se.digitalmall.model;

/**
 * 退款状态
 * @author Jerome
 *
 */
public enum RefundStatus {

	SUCCESS("退款成功"),
	WAIT_SELLER_AGREE("买家已经申请退款，等待卖家同意"),
	WAIT_BUYER_RETURN_GOODS("卖家已经同意退款，等待买家退货"),
	WAIT_SELLER_CONFIRM_GOODS("买家已经退货，等待卖家确认收货"),//即:卖家已发货
	SELLER_REFUSE_BUYER("卖家拒绝退款"),
	CLOSED("退款关闭");
    
    
	private final String info;
	private RefundStatus(String info){
		this.info = info;
	}
	public String getInfo() {
		return info;
	}	
}
