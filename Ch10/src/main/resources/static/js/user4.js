$(function(){
	
	// User4
	$('#btnUser4s').click(function(){
		$.ajax({
			url: '/Ch10/user4',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data);
				alert(JSON.stringify(data));
			}
		});
	});
	
	$('#btnUser4').click(function(){
		$.ajax({
			url: '/Ch10/user4/a102',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data);
				alert(JSON.stringify(data));
			}
		});
	});
	
	$('#btnUser4Register').click(function(){
		
		const jsonData = {
			id: 's101',
			name: '홍길동',
			gender: 'M',
			age: 22,
			addr: '서울시'
		};
		
		$.ajax({
			url: '/Ch10/user4',
			type: 'POST',
			data: jsonData,
			dataType: 'json',
			success: function(data) {
				console.log(data);
				alert(JSON.stringify(data));
			}
		});
	});
	
	$('#btnUser4Modify').click(function(){
		
		const jsonData = {
			id: 's101',
			name: '홍길동',
			gender: 'M',
			age: 24,
			addr: '서울시'
		};
		
		$.ajax({
			url: '/Ch10/user4',
			type: 'PUT',
			data: jsonData,
			dataType: 'json',
			success: function(data) {
				console.log(data);
				alert(JSON.stringify(data));
			}
		});
	});
	
	$('#btnUser4Delete').click(function(){
		$.ajax({
			url: '/Ch10/user4/s101',
			type: 'DELETE',
			dataType: 'json',
			success: function(data) {
				console.log(data);
			}
		});
	});
});