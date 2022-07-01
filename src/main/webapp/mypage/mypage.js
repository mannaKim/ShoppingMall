function go_cart(){
	if(document.formm.quantity.value==""){
		alert("수량을 입력하세요");
		document.formm.quantity.focus();
	}else{
		document.formm.action="shop.do?command=cartInsert";
		document.formm.submit();
	}
}

function go_order(){
	
}