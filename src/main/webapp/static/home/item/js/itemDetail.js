$(function(){
	$('#J_LinkBasket').click(function(){
         var add_cart_item_url = '/buy/add_cart_succeed.html';
         var price = $('.tm-promo-price > span').text();
         var qty =  $('#J_IptAmount').val();
       
         //console.log(price);
         //console.log(qty);
         input=$("<input type='hidden' name='itemSkuId' />");
		 input.attr('value','1');
		 $("#J_FrmBid").append(input);
	     input=$("<input type='hidden' name='quantity' />");
		 input.attr('value',qty);
		 $("#J_FrmBid").append(input);	
		 input=$("<input type='hidden' name='price' />");
		 input.attr('value',price);
		 $("#J_FrmBid").append(input);
		 $("#J_FrmBid").attr("action",add_cart_item_url).submit();
		 //console.log($("#J_FrmBid").html());
		 return false;
         
	});
	$('.mui-amount-increase').click(function(){
		amount=parseInt($('#J_IptAmount').val());
		amount=amount+1;
		$('#J_IptAmount').attr('value',amount);
	});
	$('.mui-amount-decrease').click(function(){
		amount=parseInt($('#J_IptAmount').val());
		if(amount>1){
			amount=amount-1;
		}		
		$('#J_IptAmount').attr('value',amount);
	});
	$('li > a').click(function(){
		if($(this).parent().parent().prop('class').indexOf('J_TSaleProp')!=-1||
				$(this).parent().parent().prop('id')=='J_UlThumb'){
			$(this).parent().parent().children().removeAttr('class');
			$(this).parent().attr('class','tb-selected');
			if($(this).parent().parent().prop('id')=='J_UlThumb')
				changeItemImageUrl($(this).children('img'));
			return false;
		}else{
			return true;
		}

	});
});
/**
 * 更换商品图片url
 */
function changeItemImageUrl(obj){
    var item_imgUrl =obj.attr('src'); 
    var img_url_len = item_imgUrl.indexOf('.jpg');
    if(img_url_len>=0){
    	item_imgUrl = item_imgUrl.substring(0,img_url_len+4);
    	$('#J_ImgBooth').attr('src', item_imgUrl + '_430x430q90.jpg' );
    }
    return false;
}
   