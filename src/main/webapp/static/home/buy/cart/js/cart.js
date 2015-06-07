$(function(){
	$("input[name='select-all']").click(function(){
		var oldClass = $('.select-all').attr('class');
		if($('.select-all').hasClass('selected')){
			$('.select-all').removeClass("selected");
			$('.cart-checkbox').removeClass("cart-checkbox-checked");
			$('.J_ItemBody ').removeClass("selected item-selected");
			
			$('#J_SmallSubmit').attr('class',"submit-btn submit-btn-disabled");
			$('#J_Go').attr('class',"submit-btn submit-btn-disabled");
			showTotalPrice();
			showSelectedCount();
		}else{
			$('.select-all').attr('class',oldClass+' selected');
			$('.cart-checkbox').attr('class','cart-checkbox'+' cart-checkbox-checked');
			oldClass = $('.J_ItemBody ').attr('class');
			$('.J_ItemBody ').attr('class',oldClass + ' selected item-selected');
			$('#J_SmallSubmit').removeClass("submit-btn-disabled");
			$('#J_Go').removeClass("submit-btn-disabled");
			showTotalPrice();
			showSelectedCount();
		}
		return false;
	});
	
	$("input[class='J_CheckBoxItem']").click(function(){
		//var checkBoxItem = $(this);
		var id = $(this).prop("id");
		var id_pos= id.indexOf('J_CheckBox');
	    if(id_pos>=0){
	    	checkBoxId = id.substring(id_pos+10);
	    }else{
	    	return false;
	    }
		var item_id = '#J_Item'+checkBoxId;
	    oldClass = $(item_id).attr('class');
		if($(item_id).hasClass('selected')){
			$(item_id).removeClass('selected item-selected');
			$(this).parent().removeClass("cart-checkbox-checked");
			$('.select-all').removeClass("selected");
			$('.select-all > .cart-checkbox').removeClass('cart-checkbox-checked');
			oldClass = $('#J_SmallSubmit').attr('class');
			if(!$('.J_ItemBody').hasClass('selected')){
				$('#J_SmallSubmit').attr('class',"submit-btn submit-btn-disabled");
				$('#J_Go').attr('class',"submit-btn submit-btn-disabled");
			}
			showTotalPrice();
			showSelectedCount();
		}else{
				
			$(item_id).attr('class',oldClass+' selected item-selected');
			oldClass = $(this).parent().attr('class');
			$(this).parent().attr('class',oldClass+' cart-checkbox-checked');
			$('#J_SmallSubmit').removeClass("submit-btn-disabled");
			$('#J_Go').removeClass("submit-btn-disabled");
			
			if($('.J_ItemBody').length==$('.J_ItemBody').filter('.selected').length){
				oldClass = $('.select-all').attr('class');
				$('.select-all').attr('class',oldClass+' selected');
				$('.cart-checkbox').attr('class','cart-checkbox'+' cart-checkbox-checked');

			}
			showTotalPrice();
			showSelectedCount();
		}
		return false;
	});
	$('.J_Plus').click(function(){
		amount=parseInt($(this).prev().val());
		amount=amount+1;
		$(this).prev().attr('value',amount);
		if(amount>1){
			$(this).prev().prev().attr('class',"J_Minus minus");
		}
		showSubTotal($(this));
		showTotalPrice();
		return false;
	});
	$('.J_Minus').click(function(){
		amount=parseInt($(this).next().val());
		if(amount>1){
			amount=amount-1;
			if(amount==1){
				$(this).attr('class','J_Minus no-minus');
			}
		}	
		$(this).next().attr('value',amount);
		showSubTotal($(this));
		showTotalPrice();
		return false;
	});
	$('#J_Go').click(function(){
 
		dynamicForm().submit(); 
	    return false;  
	});
	$('#J_SmallSubmit').click(function(){
		dynamicForm().submit(); 
	    return false; 
	});

});
/**
 * 动态创建表单，并提交给订单确认页面
 */
function dynamicForm(){
    var action = "/buy/order/confirm_order.html";  
    var form = $("<form></form>");  
    form.attr("action", action);  
    form.attr("method", "post");  
    form.attr("target", "_self");  
    var items = $("<input type='hidden' name='items' />");  
    items.attr("value", "");  
    form.append(items); 
    var buyer_from = $("<input type='hidden' name='buyer_from' />");  
    buyer_from.attr("value", "cart");  
    form.append(buyer_from);
    var source_time = $("<input type='hidden' name='source_time' />");  
    source_time.attr("value",new Date().getTime());
    form.append(source_time);
    form.appendTo("body")  
    //console.log(form.html());
    return form;
}
/**
 * 得到已选商品件数
 */
function getSelectedCount(){
	return $('.J_ItemBody').filter('.selected').length;
	
}
/**
 * 显示已选商品件数
 */
function showSelectedCount(){
	$('#J_SelectedItemsCount').html(getSelectedCount());
	
}
/**
 * 显示购物项小计价格
 */
function showSubTotal(obj){
	priceNow = obj.parents('.J_ItemBody').find('.price-now').html();
	obj.parents('.J_ItemBody').find('.J_ItemSum').html(getSubTotal(priceNow,amount).toFixed(2));

}
/**
 * 显示商品总价
 */
function showTotalPrice(){
	totalPrice = getTotalPrice().toFixed(2);
	//console.log(totalPrice);
	$('#J_SmallTotal').html(totalPrice);
	$('#J_Total').html(totalPrice);

}
/**
 * 得到商品总价,只有选中的商品才会参与总价的计算;返回number类型
 */
function getTotalPrice(){
	var totalPrice= Number("0.00");
	$('.J_ItemBody').each(function(index,domEle){
		if($(domEle).hasClass('selected')){
			priceNow = Number($(domEle).find('.price-now').html());
			//console.log($(domEle).find('.price-now').html());
			itemAmount =  Number($(domEle).find('.J_ItemAmount').val());
			//console.log(itemAmount);
			totalPrice = totalPrice.add(getSubTotal(priceNow,itemAmount));
			
		}
	});
	return totalPrice;
}
/**
 * 购物项小计价格,返回number类型
 */
function getSubTotal(priceNow,itemAmount){
	var n_priceNow = Number(priceNow);
	var n_itemAmount = Number(itemAmount);
	return n_priceNow.mul(n_itemAmount);
}
/**
 * 加法函数
 */ 
function accAdd(arg1, arg2) {  
    var r1, r2, m;  
    try {  
        r1 = arg1.toString().split(".")[1].length;  
    }  
    catch (e) {  
        r1 = 0;  
    }  
    try {  
        r2 = arg2.toString().split(".")[1].length;  
    }  
    catch (e) {  
        r2 = 0;  
    }  
    m = Math.pow(10, Math.max(r1, r2));  
    return (arg1 * m + arg2 * m) / m;  
}
/**
 * 给Number类型增加一个add方法,使用时直接用 .add 即可完成计算
 */
Number.prototype.add = function (arg) {  
    return accAdd(arg, this);  
};  
/**
 * 减法函数
 * @param arg1
 * @param arg2
 * @returns
 */  
function Subtr(arg1, arg2) {  
    var r1, r2, m, n;  
    try {  
        r1 = arg1.toString().split(".")[1].length;  
    }  
    catch (e) {  
        r1 = 0;  
    }  
    try {  
        r2 = arg2.toString().split(".")[1].length;  
    }  
    catch (e) {  
        r2 = 0;  
    }  
    m = Math.pow(10, Math.max(r1, r2));  
     //last modify by deeka  
     //动态控制精度长度  
    n = (r1 >= r2) ? r1 : r2;  
    return ((arg1 * m - arg2 * m) / m).toFixed(n);  
}  
  
/**
 * 给Number类型增加一个sub方法,使用时直接用 .sub 即可完成计算
 */
Number.prototype.sub = function (arg) {  
    return Subtr(this, arg);  
};  
  
/**
 * 乘法函数
 */  
function accMul(arg1, arg2) {  
    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();  
    try {  
        m += s1.split(".")[1].length;  
    }  
    catch (e) {  
    }  
    try {  
        m += s2.split(".")[1].length;  
    }  
    catch (e) {  
    }  
    return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);  
}   
/**
 * 给Number类型增加一个mul方法,使用时直接用 .mul 即可完成计算
 */   
Number.prototype.mul = function (arg) {  
    return accMul(arg, this);  
};   
  
/**
 * 除法函数  
 */
function accDiv(arg1, arg2) {  
    var t1 = 0, t2 = 0, r1, r2;  
    try {  
        t1 = arg1.toString().split(".")[1].length;  
    }  
    catch (e) {  
    }  
    try {  
        t2 = arg2.toString().split(".")[1].length;  
    }  
    catch (e) {  
    }  
    with (Math) {  
        r1 = Number(arg1.toString().replace(".", ""));  
        r2 = Number(arg2.toString().replace(".", ""));  
        return (r1 / r2) * pow(10, t2 - t1);  
    }  
}   
/**
 * 给Number类型增加一个div方法,使用时直接用 .div 即可完成计算
 */
Number.prototype.div = function (arg) {  
    return accDiv(this, arg);  
};