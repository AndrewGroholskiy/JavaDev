$(function(){
		var array = [];
		var totalPrice = 0;
		var totalQuantity = 0;
		
		
		if(sessionStorage.getItem("cartList") != null){
			array = JSON.parse(sessionStorage.getItem("cartList"));
			for (var i = 0; i < array.length; i++) {
				totalQuantity	= parseInt(totalQuantity)+ parseInt(array[i].quantity);
			}
			$('#cartLenght').text(totalQuantity);

			for (var i = 0; i < array.length; i++) {
				totalPrice	+= (parseInt(array[i].price) * parseInt(array[i].quantity));
			}
			$('#cartPrice').text(totalPrice);
		}else{
			
		$('#cartLenght').text('0 ');
		$('#cartPrice').text('0 ');
	}
		
		if(window.location.pathname !="/admin/item" && !window.location.pathname.includes("/admin/adminProductPage/")){
			if(sessionStorage.getItem("pagination") != null){
				sessionStorage.removeItem("pagination");
			}
		}
			
});